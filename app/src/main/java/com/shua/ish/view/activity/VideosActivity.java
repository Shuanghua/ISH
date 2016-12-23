package com.shua.ish.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shua.ish.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideosActivity extends AppCompatActivity {

    @InjectView(R.id.videoView)
    VideoView mVideoView;

    private String url;
    private MediaController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("URL_VIDEO");
            play();
        }
    }

    private void play() {
        mVideoView.setVideoPath(url);
        mController = new MediaController(this);
        mVideoView.setMediaController(mController);
        mVideoView.requestFocus();
        mVideoView.setOnCompletionListener(mp -> {
            mVideoView.stopPlayback();
            finish();
        });
    }
}
