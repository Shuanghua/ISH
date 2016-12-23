package com.shua.ish.view.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.shua.ish.R;
import com.shua.ish.app.Config;
import com.shua.ish.model.api.ImageApi;
import com.shua.ish.model.data.entity.ImageList;
import com.shua.ish.model.service.ServiceFactory;
import com.shua.ish.presenter.adapter.MeiZiAdapter;
import com.shua.ish.view.activity.PictureActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends BaseFragment implements
        RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.image_mei_zi)
    EasyRecyclerView mRecyclerView;
    @InjectView(R.id.refresh_mei_zi)
    SwipeRefreshLayout mSwipeRefresh;

    boolean first = true;
    boolean isNetWork = true;
    private SharedPreferences mSharedPreferences;

    private int page = 1;
    private MeiZiAdapter mAdapter;
    private Handler mHandler = new Handler();
    private Realm mRealm;
    private List<ImageList> mImageLists;
    private ImageApi mService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mei_zi, container,
                false);
        ButterKnife.inject(this, view);
        mSwipeRefresh.setOnRefreshListener(this);
        RealmConfiguration realmConfiguration
                = new RealmConfiguration
                .Builder(getActivity())
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        mRealm = Realm.getDefaultInstance();
        initView();
        initRealm();
        getData();
        return view;
    }

    private void initRealm() {
//        RealmConfiguration realmConfiguration
//                = new RealmConfiguration
//                .Builder(getActivity())
//                .build();
//        Realm.deleteRealm(realmConfiguration);
//        Realm.setDefaultConfiguration(realmConfiguration);
        mRealm = Realm.getDefaultInstance();
    }

    private void initView() {
        mSharedPreferences = getActivity()
                .getSharedPreferences("config",
                getActivity().MODE_PRIVATE);
        first = mSharedPreferences.getBoolean("iamge_fragment", true);
        mSwipeRefresh.setColorSchemeColors(getResources()
                .getColor(R.color.colorRed));
        mRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter
                = new MeiZiAdapter(getActivity()));
        mAdapter.setMore(R.layout.load_more, this);
        mAdapter.setOnItemClickListener(
                this::startBigImgFragment);
    }

    /**
     * Item 点击监听
     */
    private void startBigImgFragment(int position) {
        ImageList item = mAdapter.getItem(position);
        String picUrl = item.getPicUrl();
        System.out.println(picUrl);
        //有的Url接口有问题，针对有问题的Url进行字符串截取
        String[] split = picUrl.split("http://s.image.hnol.net/x/246x0/auto/");
        for (String aSplit1 : split) {
            picUrl = aSplit1;
        }
        Intent intent = new Intent(getActivity(), PictureActivity.class);
        intent.putExtra("URL_MEI_ZI", picUrl);
        intent.putExtra("TITLE_MEI_ZI", item.getTitle());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        mRealm.close();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mHandler.postDelayed(() -> {
            if (isNetWork){
                page = 1;
                mAdapter.clear();
                getData();
            }else {
                RealmResults<ImageList> itemLists = getFromRealm();
                if (itemLists.size() != 0) {
                    mAdapter.addAll(itemLists);
                }
            }
            new Handler().postDelayed(() ->
                    mSwipeRefresh.setRefreshing(false)
                    , 200);
        }, 1000);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(() -> {
            page++;
            getData();
        }, 1000);
    }

    /**
     * 获取数据
     */
    private void getData() {
        mService = ServiceFactory.getMeiZiService();
        if (null != mService) {
            mService.getImageData("10", page + "",
                    Config.SHOWAPI_APPID, Config.SHOWAPI_SIGN)
                    .map(imageData -> imageData.getShowapi_res_body().getNewslist())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(imageLists -> {
                        mRealm.beginTransaction();
                        mImageLists = mRealm.copyToRealm(imageLists);
                        mRealm.commitTransaction();
                        mAdapter.addAll(mImageLists);
                        new Handler().postDelayed(() ->
                                mSwipeRefresh.setRefreshing(false)
                                , 200);
                    }, throwable -> {
                        isNetWork = false;
                        new Handler().postDelayed(() ->
                                mSwipeRefresh.setRefreshing(false)
                                , 200);
                        Toast.makeText(getActivity(), "网络不佳，请求超时！",
                                Toast.LENGTH_SHORT).show();
                    });
        }
    }

    private RealmResults<ImageList> getFromRealm() {
        RealmQuery<ImageList> query = mRealm.where(ImageList.class);
        return query.findAll();
    }
}
