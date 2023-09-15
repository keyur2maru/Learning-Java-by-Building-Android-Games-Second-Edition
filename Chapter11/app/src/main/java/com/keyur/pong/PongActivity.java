package com.keyur.pong;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.keyur.pong.PongGame;

public class PongActivity extends Activity {
    private PongGame mPongGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        mPongGame = new PongGame(this, size.x, size.y);
        setContentView(mPongGame);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPongGame.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPongGame.pause();
    }
}