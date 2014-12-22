/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.audioengine;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import pjs4.gamefactory.events.Event;
import pjs4.gamefactory.services.AudioService;
import pjs4.gamefactory.services.Resource;
import pjs4.gamefactory.utils.RingBuffer;

/**
 *
 * @author scalpa
 */
public class AudioEngine implements AudioService {

    private final RingBuffer<AudioEvent> soundEvents;
    private final Map<AudioResource, Clip> playingSounds;

    Thread handler;
    Thread player;

    public AudioEngine() {

        this.soundEvents = new RingBuffer<>();

        this.playingSounds = new ConcurrentHashMap<>();

        this.handler = new Thread(() -> {
            while (true) {
                synchronized (soundEvents) {
                    AudioEvent event = soundEvents.get();
                    System.out.println("handler" + event.getResource().getAudioFile().getPath());
                    Clip clip = loadClipFromEvent(event);
                    if (clip != null) {
                        clip.addLineListener((LineEvent le) -> {
                            if (le.getType().equals(LineEvent.Type.STOP)) {
                                le.getLine().close();
                            }
                        });
                        synchronized (playingSounds) {
                            playingSounds.put(event.getResource(), clip);
                        }
                    }
                }
            }
        });

        this.player = new Thread(() -> {
            while (true) {
                synchronized (playingSounds) {
                    Iterator<Map.Entry<AudioResource, Clip>> iter = playingSounds.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<AudioResource, Clip> entry = iter.next();
                        AudioResource resource = entry.getKey();
                        Clip clip = entry.getValue();
                        System.out.println("player" + resource.getAudioFile().getPath());
                        if (!clip.isOpen()) {
                            iter.remove();
                        } else if (!clip.isRunning()) {
                            clip.start();
                        }

                    }
                }
            }
        });

    }

    private Clip loadClipFromEvent(AudioEvent ae) {
        synchronized (soundEvents) {
            try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(ae.getResource().getAudioFile());
                Clip clip = AudioSystem.getClip();
                clip.open(inputStream);
                return clip;
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void playSound(Resource sound) {

    }

    @Override
    public void stopSound(Resource sound) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopAllSound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNotify(Event event) {

        try {
            AudioEvent audioEvent;
            audioEvent = (AudioEvent) event;
            AudioEvent.Type type = audioEvent.getType();
            if (type.equals(AudioEvent.Type.PLAY)) {
                soundEvents.add(audioEvent);
            }
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }

    }

    public void start() {
        handler.start();
        player.start();
    }

}
