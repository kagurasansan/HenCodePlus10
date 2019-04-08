package com.hencode.kagurasansan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencode.kagurasansan.utils.Utils;

/**
 * @auther kagurasansan
 * @time 2019/4/8.9:27 AM
 * @des ${TODO}
 */
public class DashboardView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path dash = new Path();

    private static final int RADIUS = Utils.dp2px(150);
    private static final int ANGLE = 120;
    private PathDashPathEffect mPathDashPathEffect;
    private PathMeasure measure;
    private Path mPath;
    public DashboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        dash.addRect(0,0,Utils.dp2px(2),Utils.dp2px(10),Path.Direction.CCW);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPath.addArc(getWidth()/2-RADIUS,
                getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,
                getHeight()/2+RADIUS,
                90+ ANGLE /2,360- ANGLE);

        measure = new PathMeasure(mPath, false);

        mPathDashPathEffect = new PathDashPathEffect(dash,
                (measure.getLength() - Utils.dp2px(2)) / 20,0,PathDashPathEffect.Style.ROTATE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画表盘
        canvas.drawArc(
                getWidth()/2-RADIUS,
                getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,
                getHeight()/2+RADIUS,
                90+ ANGLE /2,360- ANGLE
                ,false,mPaint);

        mPaint.setPathEffect(mPathDashPathEffect);

        canvas.drawArc(
                getWidth()/2-RADIUS,
                getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,
                getHeight()/2+RADIUS,
                90+ ANGLE /2,360- ANGLE
                ,false,mPaint);

        mPaint.setPathEffect(null);

        canvas.drawLine(getWidth()/2,
                getHeight()/2,
                (float) (getWidth()/ 2 + Math.cos(Math.toRadians(getAngleForMark(20)))*Utils.dp2px(100)),
                (float) (getHeight()/ 2 + Math.sin(Math.toRadians(getAngleForMark(20)))*Utils.dp2px(100)),
                mPaint);

    }

    float getAngleForMark(int mark){
        return 90 + ANGLE / 2 + (360 - ANGLE) / 20 * mark;
    }
}
