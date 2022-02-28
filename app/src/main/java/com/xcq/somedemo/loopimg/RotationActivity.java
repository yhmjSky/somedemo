package com.xcq.somedemo.loopimg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.xcq.somedemo.MainActivity;
import com.xcq.somedemo.R;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.RotateYTransformer;


import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图
 */
public class RotationActivity extends AppCompatActivity {


    Banner banner;
    List<LoopImg> loopImgs = new ArrayList<>();
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);
        initView();
    }

    private void initView() {

        banner = findViewById(R.id.banner);
        useBanner();
        banner.start();

    }

    public void useBanner() {
        //--------------------------简单使用-------------------------------
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(this));

        //—————————————————————————如果你想偷懒，而又只是图片轮播————————————————————————
        banner.setAdapter(new BannerImageAdapter<DataBean>(DataBean.getTestData3()) {
            @Override
            public void onBindView(BannerImageHolder holder, DataBean data, int position, int size) {
                //图片加载自己实现
                Glide.with(holder.itemView)
                        .load(data.imageUrl)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("title: ", data.title);
                        Intent intent= new Intent();
                        intent.setClass(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }

                });
            }
        })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(this));
        banner.setPageTransformer(new AlphaPageTransformer());
        //设置banner圆角半径
        banner.setBannerRound(0.2f);
        //设置轮播间隔时间
        banner.setLoopTime(2300);
        //设置轮播滑动过程的时间
//        banner.setScrollTime(236);
        //更多使用方法仔细阅读文档，或者查看demo
    }

}