package com.example.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.tetris.Model.Block;

import static com.example.tetris.Model.BlockType.*;

public class NextBlockView extends View {

    public Block nextBlock;

    public NextBlockView(Context context) {
        this(context, null);
    }

    public NextBlockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        nextBlock=Block.getNextBlock();
    }

    public void setNextBlock(Block block) {
        this.nextBlock = block;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / Constant.PIXEL - 1;
        int height = getHeight() / Constant.PIXEL - 1;
        Paint paint = new Paint();
        paint.setColor(Constant.ForeColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                canvas.drawRect(x * Constant.PIXEL, y * Constant.PIXEL, (x + 1) * Constant.PIXEL, (y + 1) * Constant.PIXEL, paint);
            }
        }
        nextBlock.draw(canvas);
    }
}
