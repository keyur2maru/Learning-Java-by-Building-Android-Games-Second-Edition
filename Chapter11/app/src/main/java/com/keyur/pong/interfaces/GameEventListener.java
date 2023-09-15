package com.keyur.pong.interfaces;

public interface GameEventListener {
    void onGameOver();
    void resume();
    void pause();
    void unpause();
    void stopLoop();
    void startNewGame();
    boolean isPaused();
}