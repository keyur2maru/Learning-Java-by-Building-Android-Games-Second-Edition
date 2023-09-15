package com.keyur.pong.controllers;

import android.content.Context;
import android.graphics.RectF;

import com.keyur.pong.entities.Ball;
import com.keyur.pong.entities.Bat;
import com.keyur.pong.entities.Score;
import com.keyur.pong.interfaces.GameEventListener;
import com.keyur.pong.interfaces.SoundPlayer;
import com.keyur.pong.utils.GameConstants;
import com.keyur.pong.utils.SoundHelper;


public class GameController {
    private GameEventListener gameEventListener;
    // The game objects
    private Bat mBat;
    private Ball mBall;
    private Score mScore;
    // The SoundPlayer object
    private SoundPlayer soundPlayer;
    // Screen size in pixels
    private int mScreenX;
    private int mScreenY;

    public GameController(Context context, int screenX, int screenY) {
        mScreenX = screenX;
        mScreenY = screenY;
        mBall = new Ball(screenX);
        mBat = new Bat(screenX, screenY);
        mScore = new Score();

        soundPlayer = new SoundHelper(context);
        soundPlayer.initSounds();
    }

    public void update(long mFPS) {
        mBall.update(mFPS);
        mBat.update(mFPS);
    }

    public void reset() {
        mBall.reset(getScreenX(), getScreenY());
        mScore.reset();
    }

    public Ball getBall() {
        return mBall;
    }

    public Bat getBat() {
        return mBat;
    }

    public Score getScore() {
        return mScore;
    }

    public int getScreenX() {
        return mScreenX;
    }

    public int getScreenY() {
        return mScreenY;
    }


    public void setGameEventListener(GameEventListener listener) {
        this.gameEventListener = listener;
    }

    public void detectCollisions(){
        // Has the bat hit the ball?
        if (RectF.intersects(mBat.getRect(), mBall.getRect())) {
            // Realistic-ish bounce
            mBall.batBounce(mBat.getRect());
            mBall.increaseVelocity();
            mScore.increaseScore();
            soundPlayer.play(GameConstants.BEEP);
        }

        // Has the ball hit the edge of the screen
        // Bottom
        if (mBall.getRect().bottom > mScreenY) {
            mBall.reverseYVelocity();

            mScore.loseLife();
            soundPlayer.play(GameConstants.MISS);

            if (mScore.getLives() == 0 && gameEventListener != null) {
                gameEventListener.onGameOver();
            }
        }

        // Top
        if (mBall.getRect().top < 0) {
            mBall.reverseYVelocity();
            soundPlayer.play(GameConstants.BOOP);
        }

        // Left
        if (mBall.getRect().left < 0) {
            mBall.reverseXVelocity();
            soundPlayer.play(GameConstants.BOP);
        }

        // Right
        if (mBall.getRect().right > mScreenX) {
            mBall.reverseXVelocity();
            soundPlayer.play(GameConstants.BOP);
        }
    }
    public void setBatMovementState(int state) {
        if (state == GameConstants.STOPPED) {
            mBat.setMovementState(GameConstants.STOPPED);
        } else if (state == GameConstants.LEFT) {
            mBat.setMovementState(GameConstants.LEFT);
        } else if (state == GameConstants.RIGHT) {
            mBat.setMovementState(GameConstants.RIGHT);
        }
    }
}