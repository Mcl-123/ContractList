package com.jackie.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;


public class ListDecoration extends RecyclerView.ItemDecoration {

    private ListDecorationCallBack callBack;
    private TextPaint textPaint;
    private Paint paint;
    private int topGap;
    private Paint.FontMetrics fontMetrics;

    public ListDecoration(Context context, ListDecorationCallBack callBack) {
        this.callBack = callBack;
        Resources resources = context.getResources();
        paint = new Paint();
        paint.setColor(resources.getColor(R.color.colorAccent));

        textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(80);
        textPaint.setColor(Color.BLUE);
        textPaint.getFontMetrics();
        textPaint.setTextAlign(Paint.Align.LEFT);
        fontMetrics = new Paint.FontMetrics();
        topGap = 96;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            long groupId = callBack.getGroupId(position);
            if (groupId < 0) {
                return;
            }

            String text = callBack.getGroupFirstLine(position);
            if (position == 0 || isFirstInGroup(position)) {
                float top = view.getTop() - topGap;
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);
                c.drawText(text, left, bottom, textPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        long groupId = callBack.getGroupId(position);
        if (groupId < 0) {
            return;
        }

        if (position == 0 || isFirstInGroup(position)) {
            outRect.top = topGap;
        }
    }

    private boolean isFirstInGroup(int position) {
        if (position == 0) {
            return true;
        }

        long preGroupId = callBack.getGroupId(position -1 );
        long groupId = callBack.getGroupId(position);
        return preGroupId != groupId;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        long preGroupId, groupId = -1;
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);

            preGroupId = groupId;
            groupId = callBack.getGroupId(position);
            if (groupId < 0 || groupId == preGroupId) continue;

            String textLine = callBack.getGroupFirstLine(position).toUpperCase();
            if (textLine == null || textLine.isEmpty()) continue;

            int viewBottom = view.getBottom();
            float textY = Math.max(topGap, view.getTop());
            if (position + 1 < itemCount) { //下一个和当前不一样移动当前
                long nextGroupId = callBack.getGroupId(position + 1);
                if (nextGroupId != groupId && viewBottom < textY ) {//组内最后一个view进入了header
                    textY = viewBottom;
                }
            }
            c.drawRect(left, textY - topGap, right, textY, paint);
            c.drawText(textLine, left, textY, textPaint);
        }

    }
}
