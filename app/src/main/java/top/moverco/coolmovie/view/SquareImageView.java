package top.moverco.coolmovie.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by liuzongxiang on 18/05/2017.
 */

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {
    private boolean mSquare = true;

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSquare(boolean square) {
        mSquare = square;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mSquare) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
        }
    }

}
