package com.wq.freeze.easyseparatorexample;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by freeze on 2016/4/3.
 * item decoration for recycler view
 * only work for {@link android.support.v7.widget.LinearLayoutManager}
 */
public class EasySeparator extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private final int mOrientation;
    private final int mStartMargin;
    private final int mEndMargin;
    private final Drawable mDrawable;
    private final VectorDrawableCompat mVectorDrawable;
    private Paint mPaint;
    private final ShouldDrawChecker mChecker;
    private final int mDecorationHeight;
    private final int mTranslation;

    private EasySeparator(Builder builder) {
        mStartMargin = builder.mStartMargin;
        mEndMargin = builder.mEndMargin;
        mOrientation = builder.mOrientation;
        mDrawable = builder.mDrawable;
        mVectorDrawable = builder.mVectorDrawable;

        if (builder.mColor != 0) {
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(ContextCompat.getColor(builder.mContext, builder.mColor));
        }
        mDecorationHeight = builder.mDecorationHeight;
        mTranslation = builder.mTranslation;
        mChecker = builder.mChecker;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (mDecorationHeight == 0) return;

        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft() + mStartMargin;
        final int right = parent.getWidth() - parent.getPaddingRight() - mEndMargin;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

//            if (i == childCount - 1) return;  // to remove decoration of the last item

            final View child = parent.getChildAt(i);

            if (mChecker != null) {
                if (!mChecker.shouldDrawDecoration(parent.getChildViewHolder(child).getItemViewType())) continue;
            }

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child)) + mTranslation;
            final int bottom = top + mDecorationHeight + mTranslation;
            drawSeparator(c, left, top, right, bottom, mPaint, mDrawable, mVectorDrawable);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop() + mStartMargin;
        final int bottom = parent.getHeight() - parent.getPaddingBottom() - mEndMargin;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            if (mChecker != null) {
                if (!mChecker.shouldDrawDecoration(parent.getChildViewHolder(child).getItemViewType())) continue;
            }

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child)) + mTranslation;
            final int right = left + mDecorationHeight + mTranslation;
            drawSeparator(c, left, top, right, bottom, mPaint, mDrawable, mVectorDrawable);
        }
    }

    private void drawSeparator(Canvas c, float left, float top, float right, float bottom, @Nullable Paint paint, @Nullable Drawable drawable, @Nullable VectorDrawableCompat vectorDrawable) {
        if (paint != null) {
            c.drawRect(left, top, right, bottom, paint);
        } else if (drawable != null) {
            drawable.setBounds((int)left, (int)top, (int)right, (int)bottom);
            drawable.draw(c);
        } else if (vectorDrawable != null) {
            vectorDrawable.setBounds((int)left, (int)top, (int)right, (int)bottom);
            vectorDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            if (mChecker != null && mChecker.shouldDrawDecoration(parent.getChildViewHolder(view).getItemViewType())) {
                outRect.set(0, 0, 0, mDecorationHeight);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else {
            if (mChecker != null && mChecker.shouldDrawDecoration(parent.getChildViewHolder(view).getItemViewType())) {
                outRect.set(0, 0, mDecorationHeight, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }


    public static final class Builder {
        private final Context mContext;
        private int mOrientation;
        @ColorRes private int mColor;
        private int mStartMargin;
        private int mEndMargin;
        private Drawable mDrawable;
        private VectorDrawableCompat mVectorDrawable;
        private int mDecorationHeight;
        private int mTranslation;
        private ShouldDrawChecker mChecker;

        public Builder(Context context) {
            this.mContext = context;
        }

        /**
         * @param val recycler view orientation {@link #HORIZONTAL_LIST} {@link #VERTICAL_LIST}
         */
        public Builder orientation(int val) {
            if (val != HORIZONTAL_LIST && val != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = val;
            return this;
        }

        /**
         * @param val the separator margin (startMargin and endMargin)
         */
        public Builder decorationMargin(int val) {
            if (val < 0) val = 0;
            mStartMargin = val;
            mEndMargin = val;
            return this;
        }

        /**
         * @param val the separator startMargin (means leftMargin for HORIZONTAL_LIST, topMargin for VERTICAL_LIST)
         */
        public Builder decorationStartMargin(int val) {
            if (val < 0) val = 0;
            mStartMargin = val;
            return this;
        }

        /**
         * @param val the separator endMargin (means rightMargin for HORIZONTAL_LIST, bottomMargin for VERTICAL_LIST)
         */
        public Builder decorationEndMargin(int val) {
            if (val < 0) val = 0;
            mEndMargin = val;
            return this;
        }

        /**
         * @param color set the separator color
         */
        public Builder color(@ColorRes int color) {
            mColor = color;
            return this;
        }

        /**
         * @param val the drawable id (draw as a separator)
         */
        public Builder drawable(@DrawableRes int val) {
            if (mVectorDrawable != null) {
                throw new IllegalArgumentException("You do not have the drawable and vectorDrawable");
            }
            mDrawable = ContextCompat.getDrawable(mContext, val);
            return this;
        }

        public Builder vectorDrawable(@DrawableRes int val, @Nullable Resources.Theme theme) {
            if (mDrawable != null) {
                throw new IllegalArgumentException("You do not have the drawable and vectorDrawable");
            }
            mVectorDrawable = VectorDrawableCompat.create(mContext.getResources(), val, theme);
            return this;
        }

        /**
         * @param val separator height in pixel size
         */
        public Builder decorationHeight(int val) {
            if (val < 0) val = 0;
            mDecorationHeight = val;
            return this;
        }

        /**
         * if you have to kind of separator under same item type, you should translate a separator to make other separator is not covered
         * @param val translate this separator
         */
        public Builder translation(int val) {
            if (val == 0) val = 0;
            mTranslation = val;
            return this;
        }

        /**
         * @param checker check what kind of item should draw separator
         */
        public Builder shouldDrawChecker(ShouldDrawChecker checker) {
            mChecker = checker;
            return this;
        }

        public EasySeparator build() {
            return new EasySeparator(this);
        }
    }

    /**
     * should draw separator or not
     */
    public interface ShouldDrawChecker{
        /**
         *
         * @param viewType the item type
         * @return true draw false not draw
         */
        boolean shouldDrawDecoration(int viewType);
    }
}
