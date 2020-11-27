package com.example.catanhelper;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Region;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.Log;

import java.util.ArrayList;

public class BoardView extends AppCompatImageView {
    private Path[] hexagonPaths;
    private Path[] hexagonBorderPaths;
    private Paint mBorderPaint;
    private Integer marginX;
    private Integer marginY;
    private Float radius;
    private Board b = new Board();

    public BoardView(Context context) {
        super(context);
        init();
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.hexagonPaths = new Path[19];
        this.hexagonBorderPaths = new Path[19];
        for (int i = 0; i < 19; i++) {
            this.hexagonPaths[i] = new Path();
            this.hexagonBorderPaths[i] = new Path();
        }
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setColor(Color.WHITE);
        this.mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mBorderPaint.setStrokeWidth(10f);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
    }

    public void setRadius(float radius) {
        calculatePath(radius, 0f, 0f, null, null);
    }

    public void setBorderColor(int color) {
        this.mBorderPaint.setColor(color);
        invalidate();
    }

    private void calculatePath(float radius, float centerX, float centerY, Path path, Path bPath) {
        float halfRadius = radius / 2f;
        float triangleHeight = (float) (Math.sqrt(3.0) * halfRadius);
        if (centerX == 0 && centerY == 0) {
            centerX = getMeasuredWidth() / 2f;
            centerY = getMeasuredHeight() / 2f;
        }
        path.reset();
        path.moveTo(centerX, centerY + radius);
        path.lineTo(centerX - triangleHeight, centerY + halfRadius);
        path.lineTo(centerX - triangleHeight, centerY - halfRadius);
        path.lineTo(centerX, centerY - radius);
        path.lineTo(centerX + triangleHeight, centerY - halfRadius);
        path.lineTo(centerX + triangleHeight, centerY + halfRadius);
        path.close();

        float radiusBorder = radius - 5f;
        float halfRadiusBorder = radiusBorder / 2f;
        float triangleBorderHeight = (float) (Math.sqrt(3.0) * halfRadiusBorder);

        bPath.reset();
        bPath.moveTo(centerX, centerY + radiusBorder);
        bPath.lineTo(centerX - triangleBorderHeight, centerY + halfRadiusBorder);
        bPath.lineTo(centerX - triangleBorderHeight, centerY - halfRadiusBorder);
        bPath.lineTo(centerX, centerY - radiusBorder);
        bPath.lineTo(centerX + triangleBorderHeight, centerY - halfRadiusBorder);
        bPath.lineTo(centerX + triangleBorderHeight, centerY + halfRadiusBorder);
        bPath.close();
    }

    @Override
    public void onDraw(Canvas c) {
        for (int i = 0; i < 19; i++) {
            c.drawPath(this.hexagonPaths[i], mBorderPaint);
            c.drawPath(this.hexagonBorderPaths[i], mBorderPaint);
            //c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        }
        //super.onDraw(c);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        this.radius = Math.min(width / 7f, height / 7f);
        this.marginX = width / 6;
        this.marginY = height / 4;
        int pathCount = 0;
        int tileCount = 3;
        int increment = 1;
        for (int i = 0; i < 5; i++) {
            if (tileCount == 5) {
                increment = -1;
            }
            for (int j = 0; j < tileCount; j++) {
                calculatePath(this.radius - 20f,
                        (this.marginX + 2*j*this.radius - 10f),
                        (this.marginY + 2*i*this.radius - 10f),
                        this.hexagonPaths[pathCount],
                        this.hexagonBorderPaths[pathCount]);
                pathCount++;
            }
            tileCount += increment;
        }
        setMeasuredDimension(width, height);
    }
}