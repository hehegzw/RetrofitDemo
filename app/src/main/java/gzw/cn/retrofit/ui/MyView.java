package gzw.cn.retrofit.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import gzw.cn.retrofit.R;

/**
 * Created by gzw on 2016/6/3.
 */
public class MyView extends View {
    private Bitmap bitmap;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.abc);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect srcRect = new Rect(0,0,width,height);
        Rect disRect = new Rect(5,5,getWidth()-5,getHeight()-5);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap,srcRect,disRect,null);
        //canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-10,paint);
        RectF oval = new RectF(2,2,getWidth()-2,getHeight()-2);
        canvas.drawArc(oval,180,180,false,paint);
    }
}
