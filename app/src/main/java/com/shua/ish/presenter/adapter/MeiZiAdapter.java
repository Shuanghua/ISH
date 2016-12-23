package com.shua.ish.presenter.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.shua.ish.R;
import com.shua.ish.model.data.entity.ImageList;
import com.shua.ish.view.holder.MeiziViewHolder;


public class MeiZiAdapter extends RecyclerArrayAdapter<ImageList> {

    public MeiZiAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeiziViewHolder(parent, R.layout.item_meizi);
    }
}
