package com.generation_alpha.client;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    public static Clip playAudio(Clip clip) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
       // create new file and passed into getAudioInputAStream
        File f = new File("resources/sounds/BEAST2.wav");
        AudioInputStream as = AudioSystem.getAudioInputStream(f);
        // open the audio file
        clip.open(as);
        // start the audio file
        clip.start();
        // makes an infinite loop for the clip
        clip.loop(-1);
        return clip;
    }
    public static void stopAudio(Clip clip){
        // stops audio file and close it
        clip.stop();
        clip.close();
    }
    public static void volumeDown(Clip clip){
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
        clip.start();
    }
    public static void volumeUp(Clip clip){
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(6.0f); // increase volume by 10 decibels.
        clip.start();
    }

}
