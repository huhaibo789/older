package com.future.awaker.video.adapter.holder;

import android.support.v7.widget.RecyclerView;

import com.future.awaker.R;
import com.future.awaker.data.News;
import com.future.awaker.databinding.ItemSpecialListBinding;

/**
 * Created by ruzhan on 2017/7/15.
 */

public class SpecialListHolder extends RecyclerView.ViewHolder {

    private ItemSpecialListBinding binding;

    public SpecialListHolder(ItemSpecialListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(News bean) {
        String commentStr = String.format(itemView.getResources()
                .getString(R.string.comment_count), bean.comment);
        binding.commentTv.setText(commentStr);

        binding.setNewsItem(bean);
        binding.executePendingBindings();
    }
}
