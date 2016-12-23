package com.shua.ish.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getActivity())
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
