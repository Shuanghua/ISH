package com.shua.ish.view.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.shua.ish.R;
import com.shua.ish.model.api.NewsApi;
import com.shua.ish.model.data.entity.NewsBigImg;
import com.shua.ish.model.data.entity.NewsItemList;
import com.shua.ish.model.service.ServiceFactory;
import com.shua.ish.presenter.adapter.BannerAdapter;
import com.shua.ish.presenter.adapter.NewsAdapter;
import com.shua.ish.util.DpAndPix;
import com.shua.ish.view.activity.WebActivity;
import com.shua.ish.view.widget.ISHBannerView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment
        implements RecyclerArrayAdapter.OnLoadMoreListener{

    @InjectView(R.id.inPortantNew_recyclerView)
    EasyRecyclerView mRecyclerView;
    @InjectView(R.id.refresh_inPortantNews)
    SwipeRefreshLayout mRefreshLayout;

    private ISHBannerView mHeader;
    private NewsAdapter mAdapter;
    private BannerAdapter mBannerAdapter;
    private Handler mHandler = new Handler();
    private List<NewsBigImg> mBigImg;

    int toutuNum = 3;
    boolean isNetWork = true;

    private static final NewsApi mNewsApi = ServiceFactory.getmNewsBannerService();
    private Realm mRealm;
    private List<NewsItemList> mItemLists;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.inject(this, view);
        initRealm();
        initView();
        getBannerData();
        return view;
    }

    private void initRealm() {
        mRealm = Realm.getDefaultInstance();
    }

    private void initView() {
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorBlue));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new NewsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        setBanner();
        toutuNum = 3;
        getBannerData();
        mAdapter.setMore(R.layout.load_more, this);
        setEvent();
    }

    private void setEvent() {
        mAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            String itemType = mAdapter.getItem(position).getItemType();
            switch (itemType) {
                case "article_flag": //正常
                    intent.putExtra("url", mAdapter.getItem(position).getDetailUrl());
                    startActivity(intent);
                    break;
                case "it_flag": //直播
                    System.out.println();
                    break;
                case "classtopic_flag": //专题
                    System.out.println();
                    break;
                case "album_flag": //高清图集
                    System.out.println();
                    break;
            }
        });
    }

    public void onRefresh() {
        mRefreshLayout.setOnRefreshListener(() -> mHandler.postDelayed(() -> {
            if (isNetWork) {
                toutuNum = 3;
                mAdapter.clear();
                getBannerData();
            } else {
                RealmResults<NewsItemList> itemLists = getFromRealm();
                if (itemLists.size() != 0) {
                    mAdapter.addAll(itemLists);
                }
            }
            new Handler().postDelayed(() ->
                            mRefreshLayout.setRefreshing(false)
                    , 200);
        }, 1000));
    }

    private void getItemData(String url) {
        mNewsApi.getNewsItemData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsImportant -> {
                    mRealm.beginTransaction();
                    mItemLists = mRealm.copyToRealm(newsImportant.getItemList());
                    mRealm.commitTransaction();
                    mAdapter.addAll(mItemLists);
                    new Handler().postDelayed(() -> mRefreshLayout.setRefreshing(false), 200);
                },throwable -> new Handler()
                        .postDelayed(() -> mRefreshLayout.setRefreshing(false), 200));
    }

    private void getBannerData() {
        mNewsApi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(newsImageBanner -> {
                    mBigImg = newsImageBanner.getData().getBigImg();
                    mBannerAdapter.setBigImg(mBigImg);
                    mBannerAdapter.notifyDataSetChanged();
                    return newsImageBanner.getData().getListUrl();
                })
                .subscribe(this::getItemData, throwable -> {
                    isNetWork = false;
                    Toast.makeText(getActivity(),
                            "网络不佳，请求超时！",
                            Toast.LENGTH_SHORT).show();
                    onRefresh();
                });
    }

    /**
     * Bug:连接超时
     */
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(() -> {
            toutuNum = toutuNum + 12;
            mNewsApi.loadMoreData(toutuNum + "")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(newsItem -> {
                        mAdapter.addAll(newsItem.getItemList());
                    }, Throwable::printStackTrace);
        }, 300);
    }

    public void setBanner() {
        mAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                mHeader = new ISHBannerView(getActivity());
                mHeader.setHintView(new ColorPointHintView(getActivity(),
                        Color.YELLOW, Color.GRAY));
                mHeader.setHintPadding(0, 0, 0, (int) DpAndPix.
                        convertDpToPixel(8, getActivity()));
                mHeader.setPlayDelay(4000);
                mHeader.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.
                        LayoutParams.MATCH_PARENT,
                        (int) DpAndPix.convertDpToPixel(200, getActivity())));
                mBannerAdapter = new BannerAdapter(getActivity());
                mHeader.setAdapter(mBannerAdapter);
                return mHeader;
            }

            @Override
            public void onBindView(View headerView) {
            }
        });
    }

    private RealmResults<NewsItemList> getFromRealm() {
        RealmQuery<NewsItemList> query = mRealm.where(NewsItemList.class);
        return query.findAll();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        mRealm.close();
    }
}
