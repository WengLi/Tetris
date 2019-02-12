package com.example.tetris.Model;

public class JBlock extends Block {
    @Override
    public int[][][] GetBlock() {
        int[][][] J={
                {
                        {0,1,0,0},
                        {0,1,0,0},
                        {1,1,0,0},
                        {0,0,0,0}
                },
                {
                        {1,0,0,0},
                        {1,1,1,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {1,1,0,0},
                        {1,0,0,0},
                        {1,0,0,0},
                        {0,0,0,0}
                },
                {
                        {1,1,1,0},
                        {0,0,1,0},
                        {0,0,0,0},
                        {0,0,0,0}
                }
        };
        return J;
    }

    @Override
    public com.example.tetris.Model.BlockType GetBlockType() {
        return com.example.tetris.Model.BlockType.J;
    }
}
