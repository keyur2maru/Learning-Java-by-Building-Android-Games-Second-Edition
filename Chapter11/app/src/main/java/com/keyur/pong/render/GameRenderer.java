package com.keyur.pong.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.keyur.pong.controllers.GameController;

public class GameRenderer extends SurfaceView {
    private GameController gameController;
    // These objects are needed to do the drawing
    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    private Paint mPaint;

    // How big will the text be?
    private int mFontSize;
    private int mFontMargin;

    private long mFPS;

    public GameRenderer(Context context, SurfaceHolder holder, GameController controller, int screenX) {
        super(context);
        gameController = controller;

        // Font is 5% (1/20th) of screen width
        mFontSize = screenX / 20;
        // Margin is 1.5% (1/75th) of screen width
        mFontMargin = screenX / 75;

        // Initialize the objects
        mOurHolder = holder;
        mPaint = new Paint();
    }

    // Draw the game objects and the HUD
    public void draw(boolean debugging, long mFPS) {
        this.mFPS = mFPS;
        if (mOurHolder.getSurface().isValid()) {
            // Lock the canvas (graphics memory) ready to draw
            mCanvas = mOurHolder.lockCanvas();

            // Fill the screen with a solid color
            mCanvas.drawColor(Color.argb
                    (255, 26, 128, 182));

            // Choose a color to paint with
            mPaint.setColor(Color.argb
                    (255, 255, 255, 255));

            // Draw the bat and ball
            mCanvas.drawRect(gameController.getBall().getRect(), mPaint);
            mCanvas.drawRect(gameController.getBat().getRect(), mPaint);

            // Choose the font size
            mPaint.setTextSize(mFontSize);

            // Draw the HUD
            mCanvas.drawText("Score: " + gameController.getScore().getScore() +
                            "   Lives: " + gameController.getScore().getLives() + "   Velocity: " + gameController.getBall().getVelocityLevel(),
                    mFontMargin, mFontSize, mPaint);

            if (debugging) {
                printDebuggingText();

            }
            // Display the drawing on screen
            // unlockCanvasAndPost is a method of SurfaceView
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    private void printDebuggingText(){
        int debugSize = mFontSize / 2;
        int debugStart = 150;
        mPaint.setTextSize(debugSize);
        mCanvas.drawText("FPS: " + mFPS ,
                10, debugStart + debugSize, mPaint);
    }
}