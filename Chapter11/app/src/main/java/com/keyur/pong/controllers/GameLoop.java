package com.keyur.pong.controllers;

import com.keyur.pong.interfaces.GameEventListener;
import com.keyur.pong.render.GameRenderer;

public class GameLoop implements Runnable {
    private volatile boolean running = false;
    private GameController gameController;
    private GameRenderer gameRenderer;
    private GameEventListener gameEventListener;

    private final int MILLIS_IN_SECOND = 1000;
    // How many frames per second did we get?
    private long mFPS;

    public GameLoop(GameController gameController, GameRenderer gameRenderer) {
        this.gameController = gameController;
        this.gameRenderer = gameRenderer;
    }

    public void setGameEventListener(GameEventListener listener) {
        this.gameEventListener = listener;
    }

    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.start();

    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            // Capture the current time in milliseconds in startFrameTime
            long frameStartTime = System.currentTimeMillis();

            // Provided the game isn't paused call the update method
            if (!gameEventListener.isPaused()) {
                gameController.update(mFPS);
                gameController.detectCollisions();
            }

            gameRenderer.draw(true, mFPS);

            // How long did this frame/loop take?
            // Store the answer in timeThisFrame
            long timeThisFrame = System.currentTimeMillis() - frameStartTime;

            if (timeThisFrame > 0) {
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }
    }
}