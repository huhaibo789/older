package com.future.awaker.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.future.awaker.data.Comment;
import com.future.awaker.data.News;
import com.future.awaker.data.SpecialDetail;
import com.future.awaker.imageloader.ImageLoader;
import com.future.awaker.news.adapter.HotCommentAdapter;
import com.future.awaker.news.adapter.NewListAdapter;
import com.future.awaker.video.adapter.SpecialListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruzhan on 2017/7/15.
 */

public final class DataBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView iv, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            ImageLoader.get().load(iv, imageUrl);
        }
    }

    @BindingAdapter({"imageThumbUrl"})
    public static void loadImageThumb(ImageView iv, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            ImageLoader.get().loadThumb(iv, imageUrl);
        }
    }

    @BindingAdapter({"imageCropCircleUrl"})
    public static void loadImageCropCircle(ImageView iv, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            ImageLoader.get().loadCropCircle(iv, imageUrl);
        }
    }

    @BindingAdapter({"imageCropCircleUrlRes"})
    public static void loadImageCropCircle(ImageView iv, int resId) {
        if (resId > 0) {
            ImageLoader.get().loadCropCircle(iv, resId);
        }
    }

    @BindingAdapter({"news"})
    public static void setNews(RecyclerView recyclerView, List<News> news) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof NewListAdapter) {
            ((NewListAdapter) adapter).setRefreshData(new ArrayList<>(news));
        }
    }

    @BindingAdapter({"specialDetail"})
    public static void setSpecialDetail(RecyclerView recyclerView,
                                        SpecialDetail specialDetail) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof SpecialListAdapter) {
            ((SpecialListAdapter) adapter).setSpecialDetail(specialDetail);
        }
    }

    @BindingAdapter({"commentList"})
    public static void setCommentList(RecyclerView recyclerView,
                                      List<Comment> commentList) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof HotCommentAdapter) {
            ((HotCommentAdapter) adapter).setData(new ArrayList<>(commentList));
        }
    }

}
