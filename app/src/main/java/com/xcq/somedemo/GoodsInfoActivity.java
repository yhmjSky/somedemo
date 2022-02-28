package com.xcq.somedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GoodsInfoActivity extends AppCompatActivity {

    private MyScrollView mScroll;
    private TextView mBt1;
    private TextView mBt2;
    private TextView mBt3;
    private TextView mBt4;
    private View mView1;//可以使用相应类型，如本处对象对于的为 LinearLayout
    private View mView2;
    private View mView3;
    private View mView4;
    private int mTop1;
    private int mTop2;
    private int mTop3;
    private int mTop4;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        intView();
    }

    private void intView() {
        mScroll = (MyScrollView) findViewById(R.id.scrollView);
        mView1 =  findViewById(R.id.view_one);
        mView2 =  findViewById(R.id.view_two);
        mView3 =  findViewById(R.id.view_three);
        mView4 =  findViewById(R.id.view_four);
        mBt1 = (TextView) findViewById(R.id.bt_item1);
        mBt2 = (TextView) findViewById(R.id.bt_item2);
        mBt3 = (TextView) findViewById(R.id.bt_item3);
        mBt4 = (TextView) findViewById(R.id.bt_item4);

        mBt1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mScroll.smoothScrollTo(0,mTop1);
            }
        });
        mBt2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mScroll.smoothScrollTo(0,mTop2);
            }
        });
        mBt3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mScroll.smoothScrollTo(0,mTop3);
            }
        });
        mBt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScroll.smoothScrollTo(0,mTop4);
            }
        });

        mScroll.setOnScollChangedListener(new MyScrollView.OnScollChangedListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y<mTop2) {
                    setColor(1);
                } else if (y>=mTop2&&y<mTop3) {
                    setColor(2);
                } else if (y>=mTop3&&y<mTop4) {
                    setColor(3);
                } else if (y>=mTop4) {
                    setColor(4);
                }
            }
        });
    }

    private void setColor(int index) {
        mBt1.setTextColor(Color.BLACK);
        mBt2.setTextColor(Color.BLACK);
        mBt3.setTextColor(Color.BLACK);
        mBt4.setTextColor(Color.BLACK);
        switch (index) {
            case 1:
                mBt1.setTextColor(Color.RED);
                break;
            case 2:
                mBt2.setTextColor(Color.RED);
                break;
            case 3:
                mBt3.setTextColor(Color.RED);
                break;
            case 4:
                mBt4.setTextColor(Color.RED);
                break;
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mTop1 = mView1.getTop();
        mTop2 = mView2.getTop();
        mTop3 = mView3.getTop();
        mTop4 = mView4.getTop();
    }

}