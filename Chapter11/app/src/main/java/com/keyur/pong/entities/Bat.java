package com.keyur.pong.entities;

import android.graphics.RectF;

public class Bat {

    // These are the member variables (fields)
    public static final int STOPPED = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    // These are the member variables (fields)
    // They all have the m prefix
    // They are all private
    // because direct access is not required
    private RectF mRect;
    private float mLength;
    private float mXCoord;
    private float mBatSpeed;
    private int mScreenX;

    // Keeps track of if an how the ball is moving
    // Starting with STOPPED
    private int mBatMoving = STOPPED;

    public Bat(int sx, int sy){

        // Bat needs to know the screen
        // horizontal resolution
        // Outside of this method
        mScreenX = sx;

        // Configure the size of the bat based on
        // the screen resolution
        // One eighth the screen width
        mLength = mScreenX / 8;
        // One fortieth the screen height
        float height = sy / 40;

        // Configure the starting locaion of the bat
        // Roughly the middle horizontally
        mXCoord = mScreenX / 2;
        // The height of the bat
        // off of the bottom of the screen
        float mYCoord = sy - height;

        // Initialize mRect based on the size and position
        mRect = new RectF(mXCoord, mYCoord,
                mXCoord + mLength,
                mYCoord + height);

        // Configure the speed of the bat
        // This code means the bat can cover the
        // width of the screen in 1 second
        mBatSpeed = mScreenX;
    }

    // Return a reference to the mRect object
    public RectF getRect(){
        return mRect;
    }

    // Update the movement state passed
    // in by the onTouchEvent method
    public void setMovementState(int state){
        mBatMoving = state;
    }


    // Update the bat- Called each frame/loop
    public void update(long fps){

        // Move the bat based on the mBatMoving variable
        // and the speed of the previous frame
        if(mBatMoving == LEFT){
            mXCoord = mXCoord - mBatSpeed / fps;
        }

        if(mBatMoving == RIGHT){
            mXCoord = mXCoord + mBatSpeed / fps;
        }

        // Stop the bat going off the screen
        if(mXCoord < 0){
            mXCoord = 0;
        }

        if(mXCoord + mLength > mScreenX){
            mXCoord = mScreenX - mLength;
        }

        // Update mRect based on the results from
        // the previous code in update
        mRect.left = mXCoord;
        mRect.right = mXCoord + mLength;
    }

}