package com.hendriklouw.robocar.remote.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchRemoteView extends View {
    public static final float TOUCH_CIRCLE_RADIUS = 80f;
    public static final float CENTER_CIRCLE_RADIUS = 50f;


    private Path linePath;
    private Paint drawPaint, canvasPaint, centerPaint, linePaint;

    private int lineColor = 0x666666FF;
    private int centerColor =  0x89C99CFF;
    private int drawColor = 0xC989B6FF;

    private Canvas canvas;
    private Bitmap canvasBitmap;

    private boolean isDown = false;
    private float startX;
    private float startY;

    public TouchRemoteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {
        linePath = new Path();
        
        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeJoin(Paint.Join.ROUND);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        
        drawPaint = new Paint();
        drawPaint.setColor(drawColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        centerPaint = new Paint();
        centerPaint.setColor(centerColor);
        centerPaint.setAntiAlias(true);
        centerPaint.setStrokeWidth(10);
        centerPaint.setStyle(Paint.Style.STROKE);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBitmap);
        startX = w/2;
        startY = h/2;
        clearCanvas();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
    }

    private float determineLineToPositionX(float x) {
        if(x > startX) {
            return -TOUCH_CIRCLE_RADIUS/2;
        } else {
            return TOUCH_CIRCLE_RADIUS/2;
        }
    }

    private float determineLineToPositionY(float y) {
        if(y > startY) {
            return -TOUCH_CIRCLE_RADIUS/2;
        } else {
            return TOUCH_CIRCLE_RADIUS/2;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        float lineToX = touchX + determineLineToPositionX(touchX);
        float lineToY = touchY + determineLineToPositionY(touchY);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDown = true;
                break;
            case MotionEvent.ACTION_MOVE:
                clearCanvas();

                linePath.reset();
                linePath.moveTo(startX, startY);
                linePath.lineTo(lineToX, lineToY);
                canvas.drawPath(linePath, linePaint);

                canvas.drawCircle(touchX, touchY, TOUCH_CIRCLE_RADIUS, drawPaint);
                break;
            case MotionEvent.ACTION_UP:
                isDown = false;
                linePath.reset();
                clearCanvas();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    private void clearCanvas() {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawCircle(startX, startY, CENTER_CIRCLE_RADIUS, centerPaint);
    }
}
