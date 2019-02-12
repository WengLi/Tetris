package com.example.tetris.Model;

public class LBlock extends Block {
    @Override
    public int[][][] GetBlock() {
        int[][][] L={
                {
                        {1,0,0,0},
                        {1,0,0,0},
                        {1,1,0,0},
                        {0,0,0,0}
                },
                {
                        {0,0,1,0},
                        {1,1,1,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {1,1,0,0},
                        {0,1,0,0},
                        {0,1,0,0},
                        {0,0,0,0}
                },
                {
                        {1,1,1,0},
                        {1,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0}
                }
        };
        return L;
    }

    @Override
    public com.example.tetris.Model.BlockType GetBlockType() {
        return com.example.tetris.Model.BlockType.L;
    }
}
