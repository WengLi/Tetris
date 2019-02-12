package com.example.tetris.Model;

public class SBlock extends Block {
    @Override
    public int[][][] GetBlock() {
        int[][][] S={
                {
                        {0,1,1,0},
                        {1,1,0,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {1,0,0,0},
                        {1,1,0,0},
                        {0,1,0,0},
                        {0,0,0,0}
                }
        };
        return S;
    }

    @Override
    public com.example.tetris.Model.BlockType GetBlockType() {
        return com.example.tetris.Model.BlockType.S;
    }
}
