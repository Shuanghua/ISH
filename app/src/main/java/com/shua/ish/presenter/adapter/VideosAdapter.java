package com.shua.ish.presenter.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.shua.ish.R;
import com.shua.ish.model.data.VideosData;
import com.shua.ish.view.holder.VideosViewHolder;


public class VideosAdapter extends RecyclerArrayAdapter<VideosData.VideosDataBean.VideosFeedBean> {

    public VideosAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideosViewHolder(parent, R.layout.item_videos);
    }
}
