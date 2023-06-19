package com.example.tpnoteandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TempView extends View {

    private Paint thermometerStroke, fillThermometerPaint;
    private int temp = 40;

    public TempView(Context context) {
        super(context);
        init(context);
    }

    public TempView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TempView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int width = getWidth();
        int height = getHeight();

        //canvas.translate(0,height);

//        //Rectangle
//        float left = 10;
//        float top = -getHeight() + 100;
//        float right = 50;
//        float bottom = 0;

        //Rectangle
        float left = 10;
        float top = height;
        float right = 50;
        float bottom = height/2;

        canvas.drawRect(left, top, right, bottom, thermometerStroke);

//        float minTemp = -20;
//        float maxTemp = 40;
//        float tempRatio = (temp - minTemp) / (maxTemp - minTemp);
//        float redTop = bottom - tempRatio * (bottom - top);
//        // Dessiner le rectangle rouge
//        canvas.drawRect(left, redTop, right, bottom, fillThermometerPaint);

        float minTemp = -20;
        float maxTemp = 40;
        float tempRatio = (temp - minTemp) / (maxTemp - minTemp);
        float redBottom = top - tempRatio * (top - bottom);

        // Dessiner le rectangle rouge
        canvas.drawRect(left, top, right, redBottom, fillThermometerPaint);
    }

    private void init(Context context) {
        thermometerStroke = new Paint();
        thermometerStroke.setStyle(Paint.Style.STROKE);
        thermometerStroke.setColor(Color.BLUE);
        thermometerStroke.setStrokeWidth(5);

        fillThermometerPaint = new Paint();
        fillThermometerPaint.setStyle(Paint.Style.FILL);
        fillThermometerPaint.setColor(Color.RED);
    }

    public void setTemp(int temp) {
        this.temp = temp;
        invalidate();
    }


}
