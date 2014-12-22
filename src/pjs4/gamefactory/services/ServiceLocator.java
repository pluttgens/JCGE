/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.services;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
/**
 *
 * @author scalpa
 */
public class ServiceLocator {

    private static AudioService audioService;
    private static RandomAccessFile soundList;

    public static void provideService(Service service) {
        try {
            audioService = (AudioService) service;
        } catch (ClassCastException e) {
        }

    }

    public static void provideSounds(RandomAccessFile file) {
        if (file == null) {
            try {
                soundList = new RandomAccessFile("config\\sounds.cfg", "r");
            } catch (FileNotFoundException ex) {
                System.err.println("Imposible de charger le fichier de configuration 'sounds.cfg'.");
            }
        } else {
            soundList = file;
        }
    }

    public static AudioService getAudioService() {
        return audioService;
    }

    public static RandomAccessFile getSoundList() {
        return soundList;
    }
}
