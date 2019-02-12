package com.example.tetris.Model;

public class TBlock extends Block {
    @Override
    public int[][][] GetBlock() {
        int[][][] T={
                {
                        {0,1,0,0},
                        {1,1,1,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {1,0,0,0},
                        {1,1,0,0},
                        {1,0,0,0},
                        {0,0,0,0}
                },
                {
                        {1,1,1,0},
                        {0,1,0,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {0,1,0,0},
                        {1,1,0,0},
                        {0,1,0,0},
                        {0,0,0,0}
                }
        };
        return T;
    }

    @Override
    public com.example.tetris.Model.BlockType GetBlockType() {
        return com.example.tetris.Model.BlockType.T;
    }
}
