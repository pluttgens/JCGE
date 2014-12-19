/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.audioengine;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import pjs4.gamefactory.services.AudioService;
import pjs4.gamefactory.utils.RingBuffer;

/**
 *
 * @author scalpa
 */
public class AudioEngine implements Runnable, AudioService {

    private final RingBuffer<AudioResources> soundEvents;
    Thread thread;
    long l;

    public AudioEngine() {
        thread = new Thread(this);
        soundEvents = new RingBuffer<>();
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        AudioResources.TEST.getAudioFile();
        /**
         * try { AudioInputStream sound = AudioSystem.getAudioInputStream(new
         * File("test.wav")); AudioInputStream sound2 =
         * AudioSystem.getAudioInputStream(new File("test.wav")); Clip clip =
         * AudioSystem.getClip(); Clip clip2 = AudioSystem.getClip();
         * clip.open(sound2); clip.close(); clip.open(sound); FloatControl
         * volume = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
         * volume.setValue(0); FloatControl[] controls; l =
         * clip.getMicrosecondLength() / 1000;
         *
         * clip.start(); try { thread.sleep(l); } catch (InterruptedException
         * ex) { Logger.getLogger(AudioEngine.class.getName()).log(Level.SEVERE,
         * null, ex); } } catch (UnsupportedAudioFileException | IOException |
         * LineUnavailableException ex) { System.err.println(ex.getMessage());
         * }*
         */
        List<AudioResources> playingSounds = new LinkedList<>();
        List<Clip> playingClips = new LinkedList<>();
        while (!soundEvents.isEmpty()) {
            try {
                AudioResources soundToPlay = soundEvents.get();
                playingSounds.add(soundToPlay);
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundToPlay.getAudioFile());
                Clip clipToStart = AudioSystem.getClip();
                clipToStart.open(inputStream);
                clipToStart.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(AudioEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void playSound(AudioResource resource) {
        soundEvents.add();
    }

    @Override
    public void stopSound(int soundID) {

    }

    @Override
    public void stopAllSound() {
        thread.interrupt();
        stop();
    }

    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException ex) {

        }
    }

}
