package com.shua.ish.view.holder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.shua.ish.R;
import com.shua.ish.model.data.entity.ImageList;


/**
 * MeiziViewHolder
 * Created by ShuangHua on 2016/5/7.
 */
public class MeiziViewHolder extends BaseViewHolder<ImageList> {

    private ImageView mImageView;
    private TextView mTitle;

    public MeiziViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        mImageView = $(R.id.image_item_meizi);
        mTitle = $(R.id.image_item_meizi_title);

    }

    @Override
    public void setData(ImageList meiZiData) {
        Glide.with(getContext())
                .load(meiZiData.getPicUrl())
                .placeholder(R.drawable.base)
                .override(200,300)
                .into(mImageView);
        mTitle.setText(meiZiData.getCtime());
    }
}
