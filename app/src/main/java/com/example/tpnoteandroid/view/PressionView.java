package com.example.tpnoteandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PressionView extends View {
    private Paint thermometerStroke, fillThermometerPaint;
    private int pressure = 800;

    public PressionView(Context context) {
        super(context);
        init(context);
    }

    public PressionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PressionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int pressionMin = 800;
        int pressionMax = 1200;
        float pressureRatio = (float) (pressure - pressionMin) / (pressionMax - pressionMin);

        int width = getWidth();
        int height = getHeight();

        int numBars = 4;
        int barWidth = 20;
        int barSpacing = 10;
        int maxHeight = height / 2;

        for (int i = 0; i < numBars; i++) {
            float left = i * (barWidth + barSpacing);
            float top = height - (maxHeight / numBars) * (i + 1);
            float right = left + barWidth;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, thermometerStroke);

            int filledBars = (int) (pressureRatio * numBars);

            if (i < filledBars) {
                canvas.drawRect(left, top, right, bottom, fillThermometerPaint);
            }
        }
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

    public void setPressure(int pressure) {
        this.pressure = pressure;
        invalidate();
    }
}
