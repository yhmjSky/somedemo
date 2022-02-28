package com.xcq.somedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xcq.somedemo.view.RadarView;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private List<String> titles;
    private List<Double> data;

    RadarView radarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
    }

    public void init(){
        titles = new ArrayList<>();
        data = new ArrayList<>();
        titles.add("温度");
        titles.add("天气");
        titles.add("风向");
        titles.add("气压");
        titles.add("风速");

        radarView = findViewById(R.id.radview);
        radarView.setTitles(titles);
    }
}