/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.audioengine;

import pjs4.gamefactory.utils.audio.AudioEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.EventObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javafx.util.Pair;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import pjs4.gamefactory.services.AudioService;
import pjs4.gamefactory.utils.audio.AudioResource;
import pjs4.gamefactory.utils.collections.RingBuffer;

/**
 * AudioEngine est le moteur audio par défaut de la librairie, il permet de
 * jouer un son répertioré dans l'énum AudioRessource.
 *
 * Il recoit un AudioEvent contenant les informations sur le fichier audio et
 * l'action a effectuer avec (le jouer ou le stopper) ainsi que le volume auquel
 * celui-ci doit être joué.
 *
 * Il y'a un thread qui s'occupe de récupérer les events a partir de la liste et
 * un autre qui s'occupe de les jouer.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class AudioEngine implements AudioService {

    private final RingBuffer<AudioEvent> soundEvents;
    private final List<Pair<AudioResource, Clip>> playingSounds;

    Thread handler;
    Thread player;

    /**
     * Construit un AudioEngine et initialise les collections ainsi que les
     * threads.
     *
     * - Pascal Luttgens.
     */
    public AudioEngine() {

        this.soundEvents = new RingBuffer<>();

        this.playingSounds = Collections.synchronizedList(new LinkedList<>());

        this.handler = new Thread(
                /**
                 * Récupère l'event le plus ancien de la collection d'event pour
                 * le passer à la collection des sons en cours de lecture.
                 *
                 * - Pascal Luttgens.
                 */
                () -> {
                    while (true) {
                        synchronized (soundEvents) {
                            AudioEvent event = soundEvents.get();
                            AudioEvent.Type eventType = event.getType();
                            if (eventType.equals(AudioEvent.Type.PLAY)) {
                                Clip clip = loadClipFromEvent(event);
                                if (clip != null) {
                                    /**
                                     * Ajoute un listener sur le clip pour le
                                     * fermer lorsque sa lecture est finie.
                                     */
                                    clip.addLineListener((LineEvent le) -> {
                                        if (le.getType().equals(LineEvent.Type.STOP)) {
                                            le.getLine().close();
                                        }
                                    });
                                    synchronized (playingSounds) {
                                        playingSounds.add(new Pair<>(event.getResource(), clip));
                                    }
                                }
                            } else if (eventType.equals(AudioEvent.Type.STOP)) {
                                synchronized (playingSounds) {
                                    for (Pair<AudioResource, Clip> playingSound : playingSounds) {
                                        if (playingSound.getKey().equals(event.getResource())) {
                                            Clip clip = playingSound.getValue();
                                            clip.stop();
                                            clip.close();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                });

        this.player = new Thread(
                /**
                 * Lance la lecture des sons qui ne sont pas déjà lancés et
                 * supprime ceux qui ont fini de jouer.
                 */
                () -> {
                    while (true) {
                        synchronized (playingSounds) {
                            Iterator iter = playingSounds.iterator();
                            while (iter.hasNext()) {
                                Pair<AudioResource, Clip> pair = (Pair<AudioResource, Clip>)iter.next();
                                AudioResource resource = pair.getKey();
                                Clip clip = pair.getValue();
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

    /**
     * Retourne un clip du système à partir d'un event.
     *
     * - Pascal Luttgens.
     *
     * @param ae L'event contenant la ressource
     *
     * @return Le clip du système initialisé avec la ressource
     */
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

    /**
     * Réçoit un event et effectue l'action adéquate.
     *
     * - Pascal Luttgens.
     *
     * @param event L'event audio
     */
    @Override
    public void onNotify(EventObject event) {

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

    /**
     * Lance les processus de l'AudioEngine.
     */
    public void start() {
        handler.start();
        player.start();
    }

}
