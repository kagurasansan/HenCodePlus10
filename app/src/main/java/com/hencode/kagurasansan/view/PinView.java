package com.hencode.kagurasansan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencode.kagurasansan.utils.Utils;

/**
 * @auther kagurasansan
 * @time 2019/4/8.1:44 PM
 * @des ${TODO}
 */
public class PinView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final int RADIUS = Utils.dp2px(150);

    private RectF mRectF = new RectF();

    private int[] radius = {60,80,120,100};
    private int[] colors = {Color.parseColor("#D81B60"),
            Color.parseColor("#FFE71C37"),
            Color.parseColor("#FF398AF4"),
            Color.parseColor("#FF24D81B")};

    private static final int PULL_INDEX = 3;

    public PinView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(getWidth()/2-RADIUS,
                getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,
                getHeight()/2+RADIUS);
    }

    {
        paint.setStrokeWidth(4);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int curr = 0;
        for (int i = 0; i < colors.length; i++) {
            paint.setColor(colors[i]);
            if(i == PULL_INDEX){
                canvas.save();
                canvas.translate((float) Math.cos(Math.toRadians(curr + radius[i]/2))* Utils.dp2px(20),
                        (float) Math.sin(Math.toRadians(curr + radius[i]/2)) * Utils.dp2px(20));
            }
            canvas.drawArc(mRectF,curr,radius[i],true,paint);
            if(i == PULL_INDEX){
                canvas.restore();
            }
            curr += radius[i];
        }
    }
}
