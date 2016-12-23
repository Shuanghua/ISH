package com.shua.ish.presenter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.shua.ish.model.data.entity.NewsBigImg;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends StaticPagerAdapter {
    private Context mContext;
    private List<NewsBigImg> bigImg = new ArrayList<>();

    public BannerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext)
                .load(bigImg.get(position).getItemImage())
                .into(imageView);
        return imageView;
    }

    @Override
    public int getCount() {
        return bigImg.size() == 0 ? 0 : bigImg.size() - 1;
    }

    public void setBigImg(List<NewsBigImg> bigImg) {
        this.bigImg = bigImg;
    }
}
