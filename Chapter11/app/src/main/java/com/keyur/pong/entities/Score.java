package com.keyur.pong.entities;

import com.keyur.pong.utils.GameConstants;

public class Score {
    // The current score and lives remaining
    private int mScore = 0;
    private int mLives = GameConstants.LIVES;

    public void loseLife() {
        mLives--;
    }

    public void increaseScore() {
        mScore++;
    }

    public int getScore() {
        return mScore;
    }

    public int getLives() {
        return mLives;
    }

    public void reset() {
        mScore = 0;
        mLives = GameConstants.LIVES;
    }
}