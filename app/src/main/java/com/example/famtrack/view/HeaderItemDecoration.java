package com.example.famtrack.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famtrack.R;

public class HeaderItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "com.example.famtrack.view.HeaderItemDecoration";
    private View mHeaderView;
    private final boolean mSticky;
    private TextView tvHeader;
    private final HeaderCallback headerCallback;

    public HeaderItemDecoration(boolean mSticky, HeaderCallback headerCallback) {
        this.mSticky = mSticky;
        this.headerCallback = headerCallback;
    }

    public interface HeaderCallback {

        boolean isHeader(int position);

        String getHeader(int position);
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        Log.e(TAG, "getItemOffsets: called" );

        int position = parent.getChildAdapterPosition(view);
        if (headerCallback.isHeader(position)) {
            outRect.top = mHeaderView.getHeight();
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        Log.e(TAG, "onDrawOver: called" );

        if (mHeaderView == null) {
            mHeaderView = inflateHeader(parent);
            tvHeader = mHeaderView.findViewById(R.id.tv_header);
            fixLayoutSize(mHeaderView, parent);
        }

        String previousHeader = "";

        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            int childPos = parent.getChildAdapterPosition(child);
            String header = headerCallback.getHeader(childPos);
            tvHeader.setText(header);

            if (!previousHeader.equalsIgnoreCase(header) || headerCallback.isHeader(childPos)) {
                drawHeader(c, child, mHeaderView);
                previousHeader = header;
            }
        }
    }

    private void drawHeader(Canvas c, View child, View mHeaderView) {
        c.save();
        if (mSticky) {
            c.translate(0, Math.max(0, child.getTop() - mHeaderView.getHeight()));
        } else {
            c.translate(0, child.getTop() - mHeaderView.getHeight());
        }
        mHeaderView.draw(c);
        c.restore();
    }

    private void fixLayoutSize(View view, RecyclerView parent) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.EXACTLY);
        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingStart() + parent.getPaddingEnd(), view.getLayoutParams().width);
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec, parent.getPaddingTop() + parent.getPaddingBottom(), view.getLayoutParams().height);

        view.measure(childWidth, childHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    private View inflateHeader(RecyclerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.card_payment_header, parent, false);
    }
}
