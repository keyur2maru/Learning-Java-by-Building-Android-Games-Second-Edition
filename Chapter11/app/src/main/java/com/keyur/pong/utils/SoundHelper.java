package com.keyur.pong.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import com.keyur.pong.interfaces.SoundPlayer;
import java.io.IOException;

public class SoundHelper implements SoundPlayer {
    // All these are for playing sounds
    private SoundPool mSP;
    private int mBeepID = -1;
    private int mBoopID = -1;
    private int mBopID = -1;
    private int mMissID = -1;
    private Context myContext;
    public SoundHelper(Context context) {
        // Prepare the SoundPool instance
        // Depending upon the version of Android

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            mSP = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        myContext = context;
    }

    public void initSounds() {
        // Open each of the sound files in turn
        // and load them in to Ram ready to play
        // The try-catch blocks handle when this fails
        // and is required.

        try {
            AssetManager assetManager = myContext.getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("beep.ogg");
            mBeepID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("boop.ogg");
            mBoopID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("bop.ogg");
            mBopID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("miss.ogg");
            mMissID = mSP.load(descriptor, 0);

        } catch(IOException e) {
            Log.e("error", "failed to load sound files");
        }
    }

    // Play a sound with the given soundID
    // 1 - mBeepID
    // 2 - mMissID
    // 3 - mBoopID
    // 4 - mBopID
    public void play(int soundID) {
        switch (soundID) {
            case GameConstants.BEEP:
                mSP.play(mBeepID, 1, 1, 0, 0, 1);
                break;
            case GameConstants.MISS:
                mSP.play(mMissID, 1, 1, 0, 0, 1);
                break;
            case GameConstants.BOOP:
                mSP.play(mBoopID, 1, 1, 0, 0, 1);
                break;
            case GameConstants.BOP:
                mSP.play(mBopID, 1, 1, 0, 0, 1);
                break;
        }
    }

}
