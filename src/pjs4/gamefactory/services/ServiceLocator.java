/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.services;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import pjs4.gamefactory.assetmanager.AssetManager;

/**
 * Le locateur de service sert d'interface entre le code et les services afin de
 * les découpler et de contrôler l'instance des services.
 *
 * @author Pascal Luttgens.
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ServiceLocator {

    private static AudioService audioService;
    private static AssetManager assetManager;

    public static void provideAudioService(AudioService audioService) {
        ServiceLocator.audioService = audioService;
    }

    public static void provideAssetManager(AssetManager assetManager) {
        ServiceLocator.assetManager = assetManager;
    }

    public static AudioService getAudioService() {
        return audioService;
    }

    public static AssetManager getAssetManager() {
        return assetManager;
    }
}
