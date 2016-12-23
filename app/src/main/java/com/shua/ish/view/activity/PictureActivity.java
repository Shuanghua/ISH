package com.shua.ish.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.shua.ish.R;
import com.shua.ish.util.RxMeizhi;
import com.shua.ish.util.Shares;
import com.shua.ish.view.widget.StatusBarCompat;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.android.schedulers.AndroidSchedulers;


public class PictureActivity extends BaseActivity {

    @InjectView(R.id.picture)
    PhotoView mImageView;

    String mImageUrl, mImageTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        mToolbar.setBackgroundResource(R.color.colorRed);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorRed));
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAppBar.setAlpha(0.7f);
        initView();
    }

    @Override
    public int setLayoutResID() {
        return R.layout.activity_picture;
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            mImageUrl = intent.getStringExtra("URL_MEI_ZI");
            mImageTitle = intent.getStringExtra("TITLE_MEI_ZI");
            setTitle(mImageTitle);

            ViewCompat.setTransitionName(mImageView, "picture");
            Picasso.with(this)
                    .load(mImageUrl)
                    .into(mImageView);
        }
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        setupPicture();
    }

    private void setupPicture() {
        mImageView.enable();
        mImageView.setOnClickListener(v -> hideOrShowToolbar());


        mImageView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(PictureActivity.this)
                    .setMessage("保存到手机")
                    .setNegativeButton(android.R.string.cancel,
                            (dialog, which) -> dialog.dismiss())
                    .setPositiveButton(android.R.string.ok,
                            (dialog, which) -> {
                                saveImage();
                                dialog.dismiss();
                            })
                    .show();
            return true;
        });
    }

    private void saveImage() {
        RxMeizhi.saveImageAndGetPathObservable(this, mImageUrl, mImageTitle)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> {
                    File appDir = new File(Environment.getExternalStorageDirectory(), "ISHImage");
                    String msg = String.format(getString(R.string.picture_has_save_to),
                            appDir.getAbsolutePath());
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }, error -> Toast.makeText(this, error.getMessage() + "\n再试试...",
                        Toast.LENGTH_SHORT).show());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                RxMeizhi.saveImageAndGetPathObservable(this, mImageUrl, mImageTitle)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(uri -> Shares.shareImage(this, uri,
                                "分享到..."),
                                error -> Toast.makeText(PictureActivity.this
                                        , error.getMessage()
                                        , Toast.LENGTH_SHORT).show());
                break;
            case R.id.action_save:
                saveImage();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
