package com.future.awaker.source.local;

import com.future.awaker.data.News;
import com.future.awaker.network.EmptyConsumer;
import com.future.awaker.network.ErrorConsumer;
import com.future.awaker.util.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Copyright ©2017 by ruzhan
 */

public final class LocalDataSourceImpl implements ILocalDataSource {

    private static final String TAG = LocalDataSourceImpl.class.getSimpleName();

    @SuppressWarnings("unchecked")
    @Override
    public Flowable<RealmResults> getLocalRealm(Class clazz,
                                                HashMap<String, String> map) {
        Flowable<RealmResults> realmResultsFlowable =
                Flowable.create(emitter -> {
                    Realm realmInstance = Realm.getDefaultInstance();
                    RealmQuery query = realmInstance.where(clazz);
                    if (map != null) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            query.equalTo(entry.getKey(), entry.getValue());
                        }
                    }
                    RealmResults results = query.findAllAsync();
                    RealmChangeListener<RealmResults> listener =
                            new RealmChangeListener<RealmResults>() {
                                @Override
                                public void onChange(RealmResults ts) {
                                    if (ts.isLoaded()) {
                                        emitter.onNext(results);
                                        ts.removeChangeListener(this);
                                    }
                                }
                            };
                    results.addChangeListener(listener);
                }, BackpressureStrategy.LATEST);
        return Flowable.defer(() -> realmResultsFlowable);
    }

    @Override
    public void updateLocalRealm(RealmModel realmModel) {
        if (realmModel == null) {
            return;
        }
        Flowable.create((FlowableOnSubscribe<List<News>>) e -> {
            Realm realmInstance = Realm.getDefaultInstance();
            realmInstance.executeTransactionAsync(realm ->
                    realm.copyToRealmOrUpdate(realmModel));
        }, BackpressureStrategy.LATEST)
                .doOnError(throwable -> LogUtils.showLog(TAG,
                        "doOnError: " + throwable.toString()))
                .subscribe(new EmptyConsumer(), new ErrorConsumer());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteLocalRealm(Class clazz, Map<String, String> map) {
        Realm realmInstance = Realm.getDefaultInstance();
        RealmQuery realmQuery = realmInstance.where(clazz);

        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                realmQuery.equalTo(entry.getKey(), entry.getValue());
            }
        }
        RealmResults results = realmQuery.findAllAsync();
        RealmChangeListener<RealmResults> listener
                = new RealmChangeListener<RealmResults>() {
            @Override
            public void onChange(RealmResults realmResults) {
                if (realmResults.isLoaded()) {
                    realmInstance.executeTransaction(realm -> results.deleteAllFromRealm());
                    realmResults.removeChangeListener(this);
                }
            }
        };
        results.addChangeListener(listener);
    }

    @Override
    public void close() {
        Realm.getDefaultInstance().close();
    }

    @Override
    public void clearAll() {
        Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransaction(realm -> realm.deleteAll());
    }
}
