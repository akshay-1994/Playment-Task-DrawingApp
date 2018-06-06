package com.playment.playmenttask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class CanvasView extends View {

    private Paint mPaint;
    private Path mPath;
    private int mDrawColor;
    private int mBackgroundColor;
    private Canvas mExtraCanvas;
    private Bitmap mExtraBitmap;
    private Rect mFrame;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    String coordinates_1;


    int halfWidth;

    private int mDefaultWidth = 200;
    InitialCoordinatesModel initialCoordinatesModel = new InitialCoordinatesModel();




    public CanvasView(Context context) {
        this(context, null);
        mScaleDetector = new ScaleGestureDetector(context,new ScaleListener());
    }

    public CanvasView(Context context, AttributeSet attributeSet) {
        super(context);

        mBackgroundColor = ResourcesCompat.getColor(getResources(),
                R.color.canvasColor, null);
        mDrawColor = ResourcesCompat.getColor(getResources(),
                R.color.drawColor, null);


        // Set up the paint with which to draw.
        mPaint = new Paint();
        mPaint.setColor(mDrawColor);
        // Smoothes out edges of what is drawn without affecting shape.
        mPaint.setAntiAlias(true);
        // Dithering affects how colors with higher-precision
        // than the device are down-sampled.
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE); // default: FILL
        mPaint.setStrokeJoin(Paint.Join.ROUND); // default: MITER
        mPaint.setStrokeCap(Paint.Cap.ROUND); // default: BUTT
        mPaint.setStrokeWidth(12); // default: Hairline-width (really thin)

    }
    @Override
    protected void onSizeChanged(int width, int height,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        // Create bitmap, create canvas with bitmap, fill canvas with color.
        mExtraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mExtraCanvas = new Canvas(mExtraBitmap);
        mExtraCanvas.drawColor(mBackgroundColor);

    }

    public void setCoordinates(String coordinates){
        this.coordinates_1 = coordinates;
        Log.e("218",coordinates_1);
    }

    public String getCoordinates(){
        return coordinates_1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        // Draw the bitmap that has the saved path.
        canvas.drawBitmap(mExtraBitmap, 0, 0, null);
        canvas.scale(mScaleFactor,mScaleFactor);

        Log.e("tata","ondraw");

        // call function to get the initial coordinates
        float initialX = initialCoordinatesModel.getX();
        float initialY = initialCoordinatesModel.getY();

        Log.e("initial",String.valueOf(initialX));

        if (initialX != 0 && initialY !=0 )
        {
            drawSquare(canvas,mPaint,initialX,initialY,mDefaultWidth);
        }

        canvas.restore();

    }

    public void drawSquare(Canvas canvas, Paint paint, float x , float y, int width){

        halfWidth = width/2;
        Path path = new Path();
        path.moveTo(x-halfWidth,y+halfWidth); // Top
        path.lineTo(x+halfWidth,y+halfWidth); // Right
        path.lineTo(x+halfWidth,y-halfWidth); // Down
        path.lineTo(x-halfWidth,y-halfWidth); // Left
        path.lineTo(x-halfWidth,y+halfWidth); // again back to top
        path.close();

        canvas.drawPath(path,paint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("blah","blah");
        float x = event.getX();
        float y = event.getY();

        String coordinates = "{"+"("+String.valueOf(x-halfWidth) +" , "+ String.valueOf(y+halfWidth)+")"+" "+"("+String.valueOf(x+halfWidth) +" , "+ String.valueOf(y+halfWidth)+")"+" "+"("+String.valueOf(x+halfWidth) +" , "+ String.valueOf(y-halfWidth)+")"+" "+"("+String.valueOf(x-halfWidth) +" , "+ String.valueOf(y-halfWidth)+")"+" "+"}";
        InitialCoordinatesModel.setCoordinates1(coordinates);
        Log.e("str",coordinates);
        setCoordinates(coordinates);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                initialCoordinatesModel.setX(x);
                initialCoordinatesModel.setY(y);

                invalidate();
                Log.e("x",String.valueOf(x)+String.valueOf(y));
                break;
            case MotionEvent.ACTION_MOVE:

                initialCoordinatesModel.setX(x);
                initialCoordinatesModel.setY(y);
                invalidate();
                Log.e("x",String.valueOf(x)+String.valueOf(y));
                break;
            case MotionEvent.ACTION_UP:

                initialCoordinatesModel.setX(x);
                initialCoordinatesModel.setY(y);
                invalidate();
                Log.e("x",String.valueOf(x)+String.valueOf(y));
                break;
        }
        mScaleDetector.onTouchEvent(event);
        return true;

    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor*=detector.getScaleFactor();

            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }


}
