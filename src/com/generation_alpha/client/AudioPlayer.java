package com.generation_alpha.client;

import  javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class AudioPlayer implements Runnable {
    private static final AudioPlayer audioPlayer = new AudioPlayer();
    private volatile boolean muted = false;
    private Clip clip;

    public AudioPlayer() {
    }

    public static AudioPlayer getInstance() {
        return audioPlayer;
    }

    public static void playSound(String soundFile) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(AudioPlayer.class.getClassLoader().getResourceAsStream(soundFile));
            InputStream buffInputStream = new BufferedInputStream(inputStream);
            clip.open((AudioInputStream) buffInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startThread() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        startThread();
    }

    public void play(String fileName) {
        if (!muted) {
            run();
            playSound(fileName);
        }
    }

    public void mute() {
        setMuted(true);
        stop();
    }

    public void toggleMute() {
        boolean isMuted = getMuted();
        if (isMuted) {
            unMute();
        } else {
            mute();
        }
    }

    public void unMute() {
        setMuted(false);
    }

    public void stop() {
        if (clip.isActive()) {
            clip.stop();
            clip.close();
        }
    }

    private boolean getMuted() {
        return this.muted;
    }

    private void setMuted(Boolean muted) {
        this.muted = muted;
        // maybe need to close thread?
    }
}