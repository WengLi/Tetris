package com.example.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TetrisView tetrisView;
    private NextBlockView nextBlockView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tetrisView = (TetrisView) findViewById(R.id.tetrisView);
        nextBlockView = (NextBlockView) findViewById(R.id.nextView);
        textView = (TextView)findViewById(R.id.blockTxt);
        tetrisView.parentActivity = this;
        tetrisView.nextBlockView=nextBlockView;
        tetrisView.textView=textView;
    }

    public void moveLeft(View view) {
        tetrisView.moveLeft();
    }

    public void moveRight(View view) {
        tetrisView.moveRight();
    }

    public void rotate(View view) {
        tetrisView.rotate();
    }

    public void moveDown(View view) {
        tetrisView.moveDown();
    }

    public void startGame(View view) {
        tetrisView.startGame();
    }

    public void exit(View view) {
        tetrisView.stopGame();
        this.finish();
        System.exit(0);
    }
}
