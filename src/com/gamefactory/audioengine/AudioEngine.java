package com.gamefactory.audioengine;

import com.gamefactory.assets.types.AudioAsset;
import java.util.HashMap;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import com.gamefactory.services.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gamefactory.services.ServiceLocator;
import javax.sound.sampled.FloatControl;

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
public class AudioEngine implements Service {

    private final static String assetType = "audio";

    private final HashMap<String, Clip> playingSounds;

    /**
     * Construit un AudioEngine et initialise les collections ainsi que les
     * threads.
     *
     * - Pascal Luttgens.
     */
    public AudioEngine() {
        this.playingSounds = new HashMap<>();
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
    private Clip loadClipFromAssetName(String assetName) {
        try {
            AudioAsset audioAsset = (AudioAsset) ServiceLocator.getAssetManager().getAssetCopy(assetType, assetName);
            Clip clip = (Clip) AudioSystem.getLine(audioAsset.getInfo());
            clip.open(audioAsset.getFormat(), audioAsset.getAudioData(), 0, audioAsset.getAudioData().length);
            return clip;
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new IllegalStateException("Erreur lors du chargement de l'asset : " + assetName);
    }

    /**
     * Lance le son du jeu
     */
    public void playSound(String name, String id) {
        playSound(name, id, 50);
    }

    /**
     * Lance le son du jeu avec un systeme de volume
     * @param name
     * @param id
     * @param volume
     */
    public void playSound(String name, String id, int volume) {
        synchronized (this.playingSounds) {
            Clip clip = loadClipFromAssetName(name);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            //Changement volume
            volumeControl.setValue(volume > volumeControl.getMaximum() ? volumeControl.getMaximum() : volume);
            clip.addLineListener((LineEvent le) -> {
                if (le.getType().equals(LineEvent.Type.STOP)) {
                    long eventPos = le.getFramePosition();
                    Clip eventClip = (Clip) le.getLine();
                    if (eventPos - eventClip.getFrameLength() > -0.1
                            && eventPos - eventClip.getFrameLength() < 0.1) {
                        le.getLine().close();
                    }
                }
            });
            /**
             * Ajoute un listener sur le clip pour le fermer lorsque sa lecture
             * est finie.
             */
            clip.addLineListener((LineEvent le) -> {
                if (le.getType().equals(LineEvent.Type.CLOSE)) {
                    le.getLine().close();
                    playingSounds.remove(id);
                }
            });

            synchronized (playingSounds) {
                playingSounds.put(name + ((id != null) ? id : ""), clip);
            }

            clip.start();
        }
    }

    /**
     * Initialise le volume
     * @param name
     * @param id
     * @param volume
     */
    public void setVolume(String name, String id, int volume) {
        FloatControl volumeControl;
        synchronized (this.playingSounds) {
            volumeControl = (FloatControl) playingSounds.get(name + ((id != null) ? id : "")).getControl(FloatControl.Type.VOLUME);
        }
        // changer le volume...
    }

    /**
     * Arrete le son du jeu
     * @param name
     * @param id
     */
    public void stopSound(String name, String id) {
        synchronized (this.playingSounds) {
            playingSounds.get(name + ((id != null) ? id : "")).stop();
        }
    }

}
