package com.hencode.kagurasansan.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencode.kagurasansan.R;
import com.hencode.kagurasansan.utils.Utils;

/**
 * @auther kagurasansan
 * @time 2019/4/8.2:51 PM
 * @des ${TODO}
 */
public class AvatarView extends View {

    private Bitmap drawBitmap;
    private static final int WIDTH = Utils.dp2px(300);
    private static final int PADDING = Utils.dp2px(40);
    private static final int BOARD = Utils.dp2px(10);
    private Paint mPaint;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        drawBitmap = getAvatar(WIDTH);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#FFF7EF0A"));
        canvas.drawOval(PADDING - BOARD,PADDING - BOARD,PADDING + WIDTH + BOARD,PADDING + WIDTH + BOARD,mPaint);
        int result = canvas.saveLayer(PADDING,PADDING,PADDING + WIDTH,PADDING+WIDTH,mPaint);
        canvas.drawOval(PADDING,PADDING,PADDING + WIDTH,PADDING+WIDTH,mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(drawBitmap,PADDING,PADDING,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(result);
    }

    Bitmap getAvatar(int width){
        BitmapFactory.Options fa = new BitmapFactory.Options();
        fa.inJustDecodeBounds =  true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.a,fa);
        fa.inJustDecodeBounds = false;
        fa.inDensity = fa.outWidth;
        fa.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(),R.mipmap.a,fa);
    }


}
