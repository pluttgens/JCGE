/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.audioengine;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import pjs4.gamefactory.services.AudioService;
import pjs4.gamefactory.services.Resource;
import pjs4.gamefactory.utils.RingBuffer;

/**
 *
 * @author scalpa
 */
public class AudioRequestHandler implements Runnable, AudioService {

    private final RingBuffer<AudioEvent> soundEvents;
    private final List<Clip> loadedSounds;
    Thread thread;
    long l;

    public AudioRequestHandler() {
        thread = new Thread(this);
        soundEvents = new RingBuffer<>();
        loadedSounds = new LinkedList<>();
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            loadedSounds.add(loadClipFromEvent(soundEvents.get()));
        }

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
    }

    @Override
    public void playSound(Resource sound) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopSound(Resource sound) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopAllSound() {
        thread.interrupt();
        stop();
    }

    private Clip loadClipFromEvent(AudioEvent ae) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundEvents.get().getAudioFile());
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException ex) {

        }
    }

}
