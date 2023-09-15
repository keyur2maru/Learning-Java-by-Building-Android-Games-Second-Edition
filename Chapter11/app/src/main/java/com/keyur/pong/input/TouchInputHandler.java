package com.keyur.pong.input;

import android.view.MotionEvent;
import android.view.View;

import com.keyur.pong.controllers.GameController;
import com.keyur.pong.interfaces.GameEventListener;
import com.keyur.pong.utils.GameConstants;

public class TouchInputHandler implements View.OnTouchListener {
    private GameController gameController;
    private GameEventListener gameEventListener;

    public TouchInputHandler(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGameEventListener(GameEventListener listener) {
        this.gameEventListener = listener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:
                // If the game was paused unpause
                gameEventListener.unpause();
                // Where did the touch happen
                if (motionEvent.getX() > gameController.getScreenX() / 2) {
                    // On the right hand side
                    gameController.setBatMovementState(GameConstants.RIGHT);
                } else {
                    // On the left hand side
                    gameController.setBatMovementState(GameConstants.LEFT);
                }
                break;
            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:
                // Stop the bat moving
                gameController.setBatMovementState(GameConstants.STOPPED);
                break;
        }
        return true;
    }
}