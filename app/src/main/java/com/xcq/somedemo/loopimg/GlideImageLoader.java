package com.xcq.somedemo.loopimg;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author: xcq
 * @date: 2022/2/27
 * @github: {@link "https://github.com/yhmjSky"}
 * @version: 1.0.0
 * @description: ...
 */
public class GlideImageLoader  {

    public void displayImage(Context context, Object path, ImageView imageView) {

        Glide.with(context).load(path).into(imageView);

    }



}
