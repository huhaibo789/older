package com.future.awaker.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.future.awaker.R;
import com.future.awaker.news.fragment.NewDetailFragment;

/**
 * Copyright ©2017 by ruzhan
 */

public class NewDetailActivity extends AppCompatActivity {

    private static final String NEW_ID = "newId";
    private static final String NEW_TITLE = "newTitle";
    private static final String NEW_URL = "newUrl";

    private NewDetailFragment newDetailFragment;

    public static void launch(Context context, String newId, String newTitle, String newUrl) {
        Intent intent = new Intent(context, NewDetailActivity.class);
        intent.putExtra(NEW_ID, newId);
        intent.putExtra(NEW_TITLE, newTitle);
        intent.putExtra(NEW_URL, newUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        String newId = getIntent().getStringExtra(NEW_ID);
        String newTitle = getIntent().getStringExtra(NEW_TITLE);
        String newUrl = getIntent().getStringExtra(NEW_URL);
        if (TextUtils.isEmpty(newId)) {
            finish();
        }
        if (newDetailFragment == null) {
            newDetailFragment = NewDetailFragment.newInstance(newId, newTitle, newUrl);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_fl, newDetailFragment,
                            NewDetailFragment.class.getSimpleName())
                    .commit();
        }
    }
}
