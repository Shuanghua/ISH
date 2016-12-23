package com.shua.ish.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.shua.ish.R;
import com.shua.ish.model.api.VideosApi;
import com.shua.ish.model.data.VideosData;
import com.shua.ish.model.service.ServiceFactory;
import com.shua.ish.presenter.adapter.VideosAdapter;
import com.shua.ish.view.activity.VideosActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {

    @InjectView(R.id.videos_recycler_view)
    EasyRecyclerView mRecyclerView;
    @InjectView(R.id.videos_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private VideosAdapter mAdapter;
    private Handler mHandler = new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos,
                container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorYellow));
        mRecyclerView.setRefreshing(true, true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter = new VideosAdapter(getActivity()));
        setOnClick();
    }

    private void setOnClick() {
        mAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(getActivity(), VideosActivity.class);
            intent.putExtra("URL_VIDEO", mAdapter.getItem(position).getVideoInfo().getUrl());
            startActivity(intent);
        });

        mSwipeRefresh.setOnRefreshListener(() -> {
            mHandler.postDelayed(() -> {
                mAdapter.clear();
                getData();
                new Handler().postDelayed(() -> mSwipeRefresh.setRefreshing(false)
                        , 200);
            }, 1000);
        });
    }

    public void getData() {
        VideosApi service = ServiceFactory.getVideoService();
        Map<String, String> a = new HashMap<>();
        Map<String, String> b = new HashMap<>();
        a.put("location", "0.0%2C0.0");
        b.put("mac", "74%3Aad%3Ab7%3Aa2%3Ad6%3A50");

        service.getVideoData("news_video", "down", "2", "0", "0",
                "manual", "db48b71d9dd91f60", "6051095012", "", "", "865655021855395",
                "b207", "12030_0001", "12030_0001", "4.4.4", "2",
                "720x1280", "CHXX0008", "CMDC__CMDC__M623C", a, "",
                b, "CMDC-M623C__sinanews__5.1.0__android__4.4.4", "5070e3dc67", "239")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videos -> {
                    List<VideosData.VideosDataBean.VideosFeedBean> feed = videos.getData()
                            .getFeed();
                    mAdapter.addAll(feed);
                }, throwable -> Toast.makeText(getActivity(), "网络不佳，请求超时！",
                        Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
