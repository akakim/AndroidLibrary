package com.dream.kimlibrary.adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akakim.util.UIUtil;

public class CommonDecorator extends RecyclerView.ItemDecoration{

    private float dividerDP    = 1.0f;
    private int   dividerColor = Color.DKGRAY;
    public CommonDecorator(){

    }
    public CommonDecorator(float dp, int dividerColor) {
        super();

        this.dividerColor = dividerColor;
        this.dividerDP = dp;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawDivider(c,parent,state);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    private void drawDivider ( @NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state){

        Paint p = new Paint();
        p.setColor( dividerColor );

        for( int k =0; k< parent.getChildCount(); k++){
            View child = parent.getChildAt(k);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)child.getLayoutParams();

            int dividerTop = child.getBottom() + layoutParams.bottomMargin;
            int dividerBottom = dividerTop + UIUtil.DPToPX( parent.getContext(), dividerDP ); // Top + dividerHeight )

            c.drawRect(
                    (float)child.getLeft(),
                    (float)dividerTop,
                    (float)child.getRight(),
                    dividerBottom,
                    p
            );
        }
    }
}
