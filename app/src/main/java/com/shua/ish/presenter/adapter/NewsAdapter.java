package com.shua.ish.presenter.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.shua.ish.R;
import com.shua.ish.model.data.entity.NewsItemList;
import com.shua.ish.view.holder.NewsViewHolder;

/**
 * Created by SHUA on 2016/6/24.
 */
public class NewsAdapter extends RecyclerArrayAdapter<NewsItemList>{

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(parent, R.layout.item_news);
    }
}
