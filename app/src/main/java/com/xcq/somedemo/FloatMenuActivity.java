package com.xcq.somedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FloatMenuActivity extends AppCompatActivity {


    FloatingActionButton fab;
    RelativeLayout neirong;

    PopupWindow mPopWindow;

    boolean isFabOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_menu);
        initView();

    }


    public void initView() {

        fab = findViewById(R.id.fab);
        neirong = findViewById(R.id.neirong);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isFabOpen) {
                    openMenu();
                } else {
                    closeMenu();
                }

            }
        });

        neirong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isFabOpen){
                    closeMenu();
                }
            }
        });


    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    private void closeMenu() {

//        AlphaAnimation alphaAnimation = new AlphaAnimation(0.9f,0);
//        alphaAnimation.setDuration(500);
//        menuLL.startAnimation(alphaAnimation);
//        menuLL.setVisibility(View.GONE);

        mPopWindow.dismiss();
        backgroundAlpha(1.0f);
        isFabOpen = false;

        Log.d("close", "close");
    }

    private void openMenu() {

        popWindow();


//        menuLL.setVisibility(View.VISIBLE);
        isFabOpen = true;

        Log.d("open", "open");

    }

    private void popWindow() {
        //利用 LayoutInflater获取 R.layout.activity_popwindow 对应的 View
        View contentView = LayoutInflater.from(this).inflate(R.layout.popmenu, null);
        mPopWindow = new PopupWindow(contentView);

        /*
         * 设置窗体的长和宽
         * 代码强制设置 PopupWindow的 Height、Width(否则无法显示出来)
         * popupWindow的宽和高, 以这里代码设置的为准
         * */
        mPopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);


        backgroundAlpha(0.7f);
        //底部弹出: Gravity.BOTTOM 默认顶部，可以绑定view，于view边弹出
        mPopWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        //设置动画所对应的style
//        mPopWindow.setAnimationStyle(R.style.contextMenuAnim);
        //设置再次点击控件的时候，关闭窗口，不重新打开
        mPopWindow.setFocusable(true);
        //点击外部消失
        mPopWindow.setOutsideTouchable(true);
        //设置可以点击
        mPopWindow.setTouchable(true);

//        contentView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                int height = mPopWindow.getHeight();
//                int y = (int) event.getY();
//                System.out.println("height: "+height);
//                System.out.println("y: "+y);
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        mPopWindow.dismiss();
//                    }
//                }
//                return true;
//            }
//
//        });


    }


}