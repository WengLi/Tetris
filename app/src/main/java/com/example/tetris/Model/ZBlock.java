package com.example.tetris.Model;

public class ZBlock extends Block {
    @Override
    public int[][][] GetBlock() {
        int[][][] Z={
                {
                        {1,1,0,0},
                        {0,1,1,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {0,1,0,0},
                        {1,1,0,0},
                        {1,0,0,0},
                        {0,0,0,0}
                }
        };
        return Z;
    }

    @Override
    public com.example.tetris.Model.BlockType GetBlockType() {
        return com.example.tetris.Model.BlockType.Z;
    }
}
