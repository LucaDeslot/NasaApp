package com.example.tpnoteandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class HumidityView extends View {
    private Paint circleStrokePaint, fillCirclePaint;
    private int humidity = 0;

    public HumidityView(Context context) {
        super(context);
        init(context);
    }

    public HumidityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HumidityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        float circleRadius = Math.min(width, height) / 4;
        float centerX = width / 3;
        float centerY = height / 1.5f;

        canvas.drawCircle(centerX, centerY, circleRadius, circleStrokePaint);

        float filledCircleRadius = circleRadius * this.humidity / 100;
        canvas.drawCircle(centerX, centerY, filledCircleRadius, fillCirclePaint);
    }

    private void init(Context context) {
        circleStrokePaint = new Paint();
        circleStrokePaint.setStyle(Paint.Style.STROKE);
        circleStrokePaint.setColor(Color.BLUE);
        circleStrokePaint.setStrokeWidth(5);

        fillCirclePaint = new Paint();
        fillCirclePaint.setStyle(Paint.Style.FILL);
        fillCirclePaint.setColor(Color.RED);
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
        invalidate();
    }
}
