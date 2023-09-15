package com.keyur.pong;

import android.content.Context;
import android.view.SurfaceView;

import com.keyur.pong.controllers.GameController;
import com.keyur.pong.controllers.GameLoop;
import com.keyur.pong.render.GameRenderer;
import com.keyur.pong.input.TouchInputHandler;
import com.keyur.pong.interfaces.GameEventListener;

public class PongGame extends SurfaceView implements GameEventListener {
    private final boolean DEBUGGING = true;
    private boolean mPaused = true;

    private GameRenderer gameRenderer;
    private GameController gameController;
    private TouchInputHandler touchInputHandler;
    private GameLoop gameLoop;

    public PongGame(Context context, int screenX, int screenY) {
        super(context);
        gameController = new GameController(context, screenX, screenY);
        gameController.setGameEventListener(this);

        gameRenderer = new GameRenderer(context, getHolder(), gameController, screenX);

        touchInputHandler = new TouchInputHandler(gameController);
        this.setOnTouchListener(touchInputHandler);
        touchInputHandler.setGameEventListener(this);


        gameLoop = new GameLoop(gameController, gameRenderer);
        gameLoop.setGameEventListener(this);

        startNewGame();
    }

    @Override
    public void startNewGame() {
        pause();
        gameController.reset();
    }

    @Override
    public void stopLoop() {
        gameLoop.stop();
    }

    @Override
    public void pause() {
        mPaused = true;
    }

    @Override
    public void resume() {
        gameLoop.start();
    }

    @Override
    public void onGameOver() {
        pause();
        startNewGame();
    }

    @Override
    public boolean isPaused() {
        return mPaused;
    }

    @Override
    public void unpause() {
        mPaused = false;
    }
}
