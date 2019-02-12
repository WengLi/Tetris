package com.example.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.tetris.Model.Block;

import java.util.ArrayList;

public class TetrisView extends View {

    public Block CurrentBlock;
    public int[][] Obstacle;
    int Width = 0;
    int Height = 0;
    private Thread mainThread = null;
    private boolean runningStatus = false;
    public MainActivity parentActivity;
    public NextBlockView nextBlockView;
    public TextView textView;
    public int Score = 0;

    public TetrisView(Context context) {
        this(context, null);
    }

    public TetrisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        CurrentBlock = Block.GetRandomBlock();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Constant.ForeColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        if (Width == 0) {
            Width = getWidth() / Constant.PIXEL - 1;
            Height = getHeight() / Constant.PIXEL - 1;
        }

        if (Obstacle == null) {
            Obstacle = new int[Width][Height];
        }
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                canvas.drawRect(x * Constant.PIXEL, y * Constant.PIXEL, (x + 1) * Constant.PIXEL, (y + 1) * Constant.PIXEL, paint);
                if (Obstacle[x][y] == 1) {
                    Paint paint1 = new Paint();
                    paint1.setColor(Constant.ForeColor);
                    canvas.drawRect(x * Constant.PIXEL, y * Constant.PIXEL, (x + 1) * Constant.PIXEL, (y + 1) * Constant.PIXEL, paint1);
                }
            }
        }
        CurrentBlock.setPosition(Width / 2 - 1, -4);
        CurrentBlock.draw(canvas);
    }

    public void startGame() {
        if (mainThread == null || !mainThread.isAlive()) {
            runningStatus = true;
            Obstacle = new int[Width][Height];
            mainThread = new Thread(new MainThread());
            mainThread.start();
        }
    }

    public void stopGame() {
        runningStatus = false;
        if (mainThread != null) {
            mainThread.interrupt();
        }
    }

    public void moveLeft() {
        if (CurrentBlock.X - 1 < 0 || CurrentBlock.Y < 0) {
            return;
        }
        int[][] block = CurrentBlock.GetBlock()[CurrentBlock.Status];
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (block[y][x] == 1 && Obstacle[CurrentBlock.X + x - 1][CurrentBlock.Y + y] == 1) {
                    return;
                }
            }
        }
        CurrentBlock.moveLeft();
        invalidate();
    }

    public void moveRight() {
        if (CurrentBlock.Y < 0) {
            return;
        }
        int[][] block = CurrentBlock.GetBlock()[CurrentBlock.Status];
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (block[y][x] == 1 && CurrentBlock.X + x >= Width - 1) {
                    return;
                }
                if (block[y][x] == 1 && Obstacle[CurrentBlock.X + x + 1][CurrentBlock.Y + y] == 1) {
                    return;
                }
            }
        }
        CurrentBlock.moveRight();
        invalidate();
    }

    public void moveDown() {
        int[][] block = CurrentBlock.Block[CurrentBlock.Status];
        boolean needFixed = false;
        for (int x = 0; x < 4; x++) {
            if (needFixed == true) break;
            for (int y = 0; y < 4; y++) {
                if (block[y][x] == 1 && CurrentBlock.Y + y >= Height - 1) {
                    needFixed = true;
                    break;
                }
                if (block[y][x] == 1 && CurrentBlock.Y + y + 1 >= 0 && Obstacle[CurrentBlock.X + x][CurrentBlock.Y + y + 1] == 1) {
                    needFixed = true;
                    break;
                }
            }
        }
        if (needFixed == true) {
            fixedCurrentBlock();
            CurrentBlock = nextBlockView.nextBlock;
            nextBlockView.setNextBlock(Block.getNextBlock());
            return;
        }
        CurrentBlock.moveDown();
        invalidate();
    }

    public void rotate() {
        int status = CurrentBlock.Status + 1 >= CurrentBlock.Block.length ? 0 : CurrentBlock.Status + 1;
        int[][] block = CurrentBlock.Block[status];
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (block[y][x] == 1 && (CurrentBlock.X + x > Width - 1 || CurrentBlock.Y + y > Height - 1)) {
                    return;
                }
                if (block[y][x] == 1 && CurrentBlock.Y + y >= 0 && Obstacle[CurrentBlock.X + x][CurrentBlock.Y + y] == 1) {
                    return;
                }
            }
        }
        CurrentBlock.rotate();
        invalidate();
    }

    public void fixedCurrentBlock() {
        int[][] block = CurrentBlock.GetBlock()[CurrentBlock.Status];
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (block[y][x] == 1 && CurrentBlock.Y + y >= 0) {
                    Obstacle[CurrentBlock.X + x][CurrentBlock.Y + y] = 1;
                }
            }
        }
        invalidate();
    }

    public void deleteBlockLine() {
        ArrayList<Integer> offsetLines = new ArrayList<Integer>();
        for (int y = Height - 1; y >= 0; y--) {
            boolean needDelete = true;
            for (int x = 0; x < Width; x++) {
                if (Obstacle[x][y] == 0) {
                    needDelete = false;
                    break;
                }
            }
            if (needDelete == true) {
                offsetLines.add(y);
            }
        }
        if (offsetLines.size() <= 0) {
            return;
        }
        Score += 10 * offsetLines.size();
        textView.setText("" + Score);
        int[][] newArray = new int[Width][Height];
        for (int y = 0; y < Height; y++) {
            int down = getDownCount(offsetLines, y);
            if (down != -1) {
                for (int x = 0; x < Width; x++) {
                    if (Obstacle[x][y] == 1) {
                        newArray[x][y + down] = 1;
                    }
                }
            }
        }
        Obstacle = newArray;
    }

    private int getDownCount(ArrayList<Integer> offsetLines, int y) {
        int down = 0;
        for (int n : offsetLines) {
            if (n == y) {
                return -1;
            }
            if (y < n) {
                down++;
            }
        }
        return down;
    }

    public boolean isEndPoint() {
        for (int x = 0; x < Width; x++) {
            if (Obstacle[x][0] == 1) {
                return true;
            }
        }
        return false;
    }

    public void drawShape(Canvas canvas) {
        CurrentBlock.draw(canvas);
    }

    private class MainThread implements Runnable {
        @Override
        public void run() {
            while (runningStatus) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isEndPoint()) {
                    stopGame();
                    return;
                }
                moveDown();
                deleteBlockLine();
                parentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        invalidate();
                    }
                });
            }
        }
    }
}
