package com.shua.ish.view.holder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.shua.ish.R;
import com.shua.ish.model.data.VideosData;

/**
 * Created by SHUA on 2016/6/13.
 */
public class VideosViewHolder extends BaseViewHolder<VideosData.VideosDataBean.VideosFeedBean> {

    private ImageView mImageView;
    private TextView mTitle;

    public VideosViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        mImageView = $(R.id.item_videos_image);
        mTitle = $(R.id.item_videos_title);
    }


    @Override
    public void setData(VideosData.VideosDataBean.VideosFeedBean feedBean) {
        super.setData(feedBean);
        Glide.with(getContext())
                .load(feedBean.getVideoInfo().getPic())
                .into(mImageView);
        mTitle.setText(feedBean.getTitle());
    }
}
