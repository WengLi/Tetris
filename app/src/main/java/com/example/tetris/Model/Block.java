package com.example.tetris.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.tetris.Constant;

import java.util.Random;

public abstract class Block {
    public int X;
    public int Y;
    public int Status;
    public int[][][] Block;
    public BlockType BlockType;
    private boolean isSetPosition = false;

    public Block() {
        Block = GetBlock();
        BlockType = GetBlockType();
    }

    public void InitPosition(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public void setPosition(int x, int y) {
        if (isSetPosition == false) {
            this.X = x;
            this.Y = y;
            isSetPosition = true;
        }
    }

    public void moveLeft() {
        X--;
    }

    public void moveRight() {
        X++;
    }

    public void moveDown() {
        Y++;
    }

    public void rotate() {
        Status++;
        if (Status >= Block.length) {
            Status = 0;
        }
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Constant.ForeColor);
        if (Block.length == 0) {
            return;
        }
        int[][] shape = Block[Status];
        for (int y = 0; y < shape.length; y++) {
            int[] block = shape[y];
            for (int x = 0; x < block.length; x++) {
                if (block[x] == 1) {
                    canvas.drawRect((X + x) * Constant.PIXEL, (Y + y) * Constant.PIXEL, (X + x + 1) * Constant.PIXEL, (Y + y + 1) * Constant.PIXEL, paint);
                }
            }
        }
    }

    public static Block GetRandomBlock() {
        Random random = new Random();
        int nextInt = random.nextInt(7);
        switch (nextInt) {
            case 1:
                return new IBlock();
            case 2:
                return new JBlock();
            case 3:
                return new LBlock();
            case 4:
                return new OBlock();
            case 5:
                return new SBlock();
            case 6:
                return new TBlock();
            default:
                return new ZBlock();
        }
    }

    public static Block getNextBlock() {
        Block result = GetRandomBlock();
        switch (result.BlockType) {
            case I:
                result.InitPosition(1, 0);
                break;
            case J:
                result.InitPosition(1, 0);
                break;
            case L:
                result.InitPosition(1, 0);
                break;
            case O:
                result.InitPosition(1, 1);
                break;
            case S:
                result.InitPosition(0, 1);
                break;
            case T:
                result.InitPosition(0, 1);
                break;
            case Z:
                result.InitPosition(0, 1);
                break;
        }
        return result;
    }

    public static Block Empty() {
        return new Block() {
            @Override
            public int[][][] GetBlock() {
                return new int[0][][];
            }

            @Override
            public com.example.tetris.Model.BlockType GetBlockType() {
                return com.example.tetris.Model.BlockType.None;
            }
        };
    }

    public abstract int[][][] GetBlock();

    public abstract BlockType GetBlockType();
}
