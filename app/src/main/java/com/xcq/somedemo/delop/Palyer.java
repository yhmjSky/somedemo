package com.xcq.somedemo.delop;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.xcq.somedemo.R;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * @author: xcq
 * @date: 2022/2/17
 * @github: {@link "https://github.com/yhmjSky"}
 * @version: 1.0.0
 * @description: ...
 */

public class Palyer extends AppCompatActivity {

    private SurfaceView surfaceView;
    private IjkMediaPlayer mPlayer;
    private  SurfaceHolder.Callback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_player);
        surfaceView = findViewById(R.id.surface_view);
        init();
        surfaceView.getHolder().addCallback(callback);
    }

    private void createPlayer() {
        if (mPlayer == null) {
            mPlayer = new IjkMediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mPlayer.setDataSource("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4");
            } catch (IOException e) {
                e.printStackTrace();
            }
            mPlayer.prepareAsync();
        }
    }

    private void release() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        IjkMediaPlayer.native_profileEnd();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        release();
    }

    void init(){
         callback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createPlayer();
                mPlayer.setDisplay(surfaceView.getHolder());
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (surfaceView != null) {
                    surfaceView.getHolder().removeCallback(callback);
                    surfaceView = null;
                }
            }
        };
    }
}
