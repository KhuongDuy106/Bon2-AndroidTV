package bon2.androidtv.view;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;

import bon2.androidtv.R;
import bon2.androidtv.activity.HomeActivity;
import bon2.androidtv.helper.RoundedImageView;

public class TVRecyclerViewWishlist extends RecyclerView {
    private static final String TAG = "TVRecyclerView";

    public TVRecyclerViewWishlist(Context context) {
        this(context, null);
    }

    public TVRecyclerViewWishlist(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TVRecyclerViewWishlist(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        //setHasFixedSize(true);
        //setWillNotDraw(true);
        setChildrenDrawingOrderEnabled(true);

        setClipChildren(false);
        setClipToPadding(false);

        setItemAnimator(null);
    }

    private OnInterceptListener mOnInterceptListener;

    public void setOnInterceptListener(OnInterceptListener onInterceptListener) {
        mOnInterceptListener = onInterceptListener;
    }

    @Override
    public boolean isInTouchMode() {
        boolean result = super.isInTouchMode();
        if (Build.VERSION.SDK_INT == 19) {
            return !(hasFocus() && !result);
        } else {
            return result;
        }
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        View view = getFocusedChild();

        int position = indexOfChild(view);
        if (position < 0) {
            return i;
        } else {
            if (i == childCount - 1) {
                return position;
            }
            if (i == position) {
                return childCount - 1;
            }
        }
        return i;
    }

    public interface OnInterceptListener {
        boolean onIntercept(KeyEvent event);
    }

}
