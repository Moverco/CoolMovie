package top.moverco.coolmovie.view;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by liuzongxiang on 11/05/2017.
 */

public class MovieGroup extends ViewGroup {

    public MovieGroup(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();

    }
}
