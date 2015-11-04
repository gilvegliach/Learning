package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Gil
 */
public class ThreeViewsLLM extends LinearLayoutManager {
    public ThreeViewsLLM(Context context) {
        super(context);
    }

    public ThreeViewsLLM(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public ThreeViewsLLM(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // Taken from LayoutManager: we need to copy this method here to call our own
    // implementation of getChildMeasureSpec(), because it is static and cannot be overridden
    @Override
    public void measureChildWithMargins(View child, int widthUsed, int heightUsed) {
        final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();

        final Rect insets = mRecyclerView.getItemDecorInsetsForChild(child);
        widthUsed += insets.left + insets.right;
        heightUsed += insets.top + insets.bottom;

        final int widthSpec = getChildMeasureSpec(getWidth(),
                getPaddingLeft() + getPaddingRight() +
                        lp.leftMargin + lp.rightMargin + widthUsed, lp.width,
                canScrollHorizontally());
        final int heightSpec = getChildMeasureSpec(getHeight(),
                getPaddingTop() + getPaddingBottom() +
                        lp.topMargin + lp.bottomMargin + heightUsed, lp.height,
                canScrollVertically());
        child.measure(widthSpec, heightSpec);
    }

    public static int getChildMeasureSpec(int parentSize, int padding, int childDimension,
                                          boolean canScroll) {
        int size = Math.max(0, parentSize - padding);
        int resultSize = 0;
        int resultMode = 0;

        if (canScroll) {
            // In the direction of scrolling set the size to 1/3
                resultSize = parentSize / 3;
                resultMode = View.MeasureSpec.EXACTLY;
        } else {
            // Otherwise same logic as in LayoutManager.getChildMeasureSpec()
            if (childDimension >= 0) {
                resultSize = childDimension;
                resultMode = View.MeasureSpec.EXACTLY;
            } else if (childDimension == RecyclerView.LayoutParams.FILL_PARENT) {
                resultSize = size;
                resultMode = View.MeasureSpec.EXACTLY;
            } else if (childDimension == RecyclerView.LayoutParams.WRAP_CONTENT) {
                resultSize = size;
                resultMode = View.MeasureSpec.AT_MOST;
            }
        }
        return View.MeasureSpec.makeMeasureSpec(resultSize, resultMode);
    }
}
