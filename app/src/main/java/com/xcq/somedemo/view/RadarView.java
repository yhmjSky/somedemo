package com.xcq.somedemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xcq
 * @date: 2022/2/10
 * @github: {@link "https://github.com/yhmjSky"}
 * @version: 1.0.0
 * @description: ...
 */
public class RadarView extends View {

    private int count = 5;
    private float radius = (float) (Math.PI/2*0.7f);
    private float angle;
    private int centerX;
    private int centerY;
    //雷达区画笔
    private Paint mainPaint;
    //文本画笔
    private Paint textPaint;
    //数据区画笔
    private Paint valuePaint;

    private List<String> titles;
    private List<Double> value;
    private float maxValue = 100;             //数据最大值

    public RadarView(Context context) {
        super(context);
        init();
    }


    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mainPaint = new Paint();
        mainPaint.setColor(Color.BLACK);
        mainPaint.setAntiAlias(true);
        mainPaint.setStrokeWidth(1);
        mainPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(30);
        textPaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);

        valuePaint=new Paint();
//        valuePaint.setColor(Color.YELLOW);
        valuePaint.setColor(0xB0E2FF);
        valuePaint.setAntiAlias(true);
        valuePaint.setStyle(Paint.Style.FILL);

        titles = new ArrayList<>(count);
        titles.add("aaa");
        titles.add("bbb");
        titles.add("ccc");
        titles.add("ddd");
        titles.add("eee");


        value =new ArrayList<>();
        value.add(60.0);
        value.add(70.0);
        value.add(55.0);
        value.add(65.0);
        value.add(68.0);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w)/2*0.7f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        //1度=1*PI/180   360度=2*PI   那么我们每旋转一次的角度为2*PI/内角个数
        //中心与相邻两个内角相连的夹角角度
        angle = (float) (2 * Math.PI / count);
        //每个蛛丝之间的间距
        float r = radius / (count - 1);
        for (int i = 0; i < count; i++) {

            float curR = r * i;//当前半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //对于直角三角形sin(x)是对边比斜边，cos(x)是底边比斜边，tan(x)是对边比底边
                    //因此可以推导出:底边(x坐标)=斜边(半径)*cos(夹角角度)
                    //               对边(y坐标)=斜边(半径)*sin(夹角角度)
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }

    private void drawLines(Canvas canvas){
        Path path = new Path();
        for(int i=0;i<count;i++){
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX+radius*Math.cos(angle*i));
            float y = (float) (centerY+radius*Math.sin(angle*i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 文字标题
     * @param canvas
     */
    private void drawText(Canvas canvas){

        //http://mikewang.blog.51cto.com/3826268/871765/
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for(int i=0;i<count;i++){
            float x = (float) (centerX+(radius+fontHeight/2)*Math.cos(angle*i));
            float y = (float) (centerY+(radius+fontHeight/2)*Math.sin(angle*i));
            if(angle*i >= 0 && angle*i <= Math.PI/2){//右下
                float dis = textPaint.measureText(titles.get(i));
                canvas.drawText(titles.get(i), x,y,textPaint);

            }else if(angle*i >= 3*Math.PI/2 && angle*i <= Math.PI*2){//右上
                float dis = textPaint.measureText(titles.get(i));
                canvas.drawText(titles.get(i), x,y,textPaint);

            }else if(angle*i > Math.PI/2 && angle*i <= Math.PI){//左下
                float dis = textPaint.measureText(titles.get(i));
                canvas.drawText(titles.get(i), x-dis/2,y,textPaint);

            }else if(angle*i >= Math.PI && angle*i < 3*Math.PI/2){//左上
                float dis = textPaint.measureText(titles.get(i));
                canvas.drawText(titles.get(i), x-dis/2,y,textPaint);
            }
        }
    }

    /**
     * 绘制区域
     * @param canvas
     */
    private void drawRegion(Canvas canvas){
        Path path = new Path();
        valuePaint.setAlpha(255);
        for(int i=0;i<count;i++){

            double percent = value.get(i)/maxValue;
            float x = (float) (centerX + radius * Math.cos(angle*i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle*i) * percent);
            if(i==0){
                path.moveTo(x, centerY);
            }else{
                path.lineTo(x,y);
            }
            //绘制小圆点
            canvas.drawCircle(x,y,10,valuePaint);
        }
        path.close();
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }


    //设置标题
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    //设置数值
    public void setValue(List<Double> value) {
        this.value = value;
    }

    public void setDataList(List<String> titles,List<Double> data) {
        this.titles = titles;
        this.value = data;
    }

    //设置最大数值
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    //设置蜘蛛网颜色
    public void setMainPaintColor(int color){
        mainPaint.setColor(color);
    }

    //设置标题颜色
    public void setTextPaintColor(int color){
        textPaint.setColor(color);
    }

    //设置覆盖局域颜色
    public void setValuePaintColor(int color){
        valuePaint.setColor(color);
    }



}
