package com.generation_alpha.client;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    public static Clip playAudio(Clip clip) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
       // create new file and passed into getAudioInputAStream
        File f = new File("resources/sounds/classic.wav");
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
        clip.stop();
        clip.close();
    }

}
