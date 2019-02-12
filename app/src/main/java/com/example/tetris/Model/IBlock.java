package com.example.tetris.Model;

public class IBlock extends Block {
    @Override
    public int[][][] GetBlock() {
        int[][][] I={
                {
                        {1,0,0,0},
                        {1,0,0,0},
                        {1,0,0,0},
                        {1,0,0,0}
                },
                {
                        {0,0,0,0},
                        {1,1,1,1},
                        {0,0,0,0},
                        {0,0,0,0}
                }
        };
        return I;
    }

    @Override
    public com.example.tetris.Model.BlockType GetBlockType() {
        return com.example.tetris.Model.BlockType.I;
    }
}
