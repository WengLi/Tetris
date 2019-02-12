package com.example.tetris.Model;

public class OBlock extends Block {
    @Override
    public int[][][] GetBlock() {
        int[][][] O={
                {
                        {1,1,0,0},
                        {1,1,0,0},
                        {0,0,0,0},
                        {0,0,0,0}
                }
        };
        return O;
    }

    @Override
    public com.example.tetris.Model.BlockType GetBlockType() {
        return com.example.tetris.Model.BlockType.O;
    }
}
