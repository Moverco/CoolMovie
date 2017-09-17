package top.moverco.mviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by liuzongxiang on 18/05/2017.
 */

public class CaptionedImageView extends FrameLayout implements View.OnLayoutChangeListener {
    private Drawable mDrawable;
    private TextView mTextView;
    private SquareImageView mImageView;
    private int mScrimColor;

    public CaptionedImageView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CaptionedImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CaptionedImageView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CaptionedImageView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public SquareImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (v.getVisibility() != VISIBLE) {
            return;
        }
        final int height = bottom - top + getTextView().getHeight();
        final int width = right - left;
        if (height == 0 || width == 0) {
            return;
        }
        updateBlur();
    }

    public void setImageResource(@DrawableRes int drawableResourceId) {
        mDrawable = getResources().getDrawable(drawableResourceId);
        mImageView.setImageResource(drawableResourceId);
        updateBlur();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateBlur() {
        if (!(mDrawable instanceof BitmapDrawable)) {
            return;
        }
        final int textViewHeight = mTextView.getHeight();
        if (textViewHeight == 0) {
            return;
        }
        final float ratio = (float) textViewHeight / mImageView.getHeight();
        final BitmapDrawable bitmapDrawable = (BitmapDrawable) mDrawable;
        final Bitmap originalBitmap = bitmapDrawable.getBitmap();
        int height = (int) (ratio * originalBitmap.getHeight());
        final int y = originalBitmap.getHeight() - height;
        final Bitmap portionToBlur = Bitmap.createBitmap(originalBitmap, 0, y, originalBitmap.getWidth(), height);
        final Bitmap blurredBitmap = portionToBlur.copy(Bitmap.Config.ARGB_8888, true);

        RenderScript script = RenderScript.create(getContext());
        ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(script, Element.U8_4(script));
        Allocation tmpIn = Allocation.createFromBitmap(script, portionToBlur);
        Allocation tmpOut = Allocation.createFromBitmap(script, blurredBitmap);
        intrinsicBlur.setRadius(25f);
        intrinsicBlur.setInput(tmpIn);
        intrinsicBlur.forEach(tmpOut);
        tmpOut.copyTo(blurredBitmap);
        new Canvas(blurredBitmap).drawColor(mScrimColor);

        final Bitmap newBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        final Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(blurredBitmap, 0, y, new Paint());
        mImageView.setImageBitmap(newBitmap);
    }

    private void init(Context context) {
        /**
        inflate(context, R.layout.captioned_image, this);
        mTextView = (TextView) findViewById(R.id.captioned_text);
        mImageView = (SquareImageView) findViewById(R.id.captioned_img);
        mScrimColor = getResources().getColor(R.color.background);
        mTextView.addOnLayoutChangeListener(this);
         */
    }
}
