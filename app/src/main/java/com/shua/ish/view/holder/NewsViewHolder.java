package com.shua.ish.view.holder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.shua.ish.R;
import com.shua.ish.model.data.entity.NewsItemList;

/**
 * Created by SHUA on 2016/6/24.
 */
public class NewsViewHolder extends BaseViewHolder<NewsItemList> {

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mTime;

    public NewsViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        mImageView = $(R.id.item_news_imageView);
        mTitle = $(R.id.item_news_title);
        mTime = $(R.id.item_news_time);
    }

    @Override
    public void setData(NewsItemList data) {
        Glide.with(getContext())
                .load(data.getItemImage().getImgUrl1())
                .into(mImageView);
        mTitle.setText(data.getItemTitle());
        mTime.setText(data.getOperate_time());
    }
}
