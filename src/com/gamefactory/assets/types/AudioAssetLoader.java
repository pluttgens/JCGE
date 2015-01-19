/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;
import com.gamefactory.assets.assetmanager.AssetInputStreamProvider;
import com.gamefactory.assets.assetmanager.TypeLoader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * La classe AudioAssetLoader permet de créer un AudioAsset optimisé pour son
 * stockage et son utilisation pour le moteur à paritr d'un InputStreamWithMime.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class AudioAssetLoader implements TypeLoader {

    /**
     * Retourne un AudioAsset à partir d'un InputStream. Le MIME est inutile
     * ici.
     *
     * - Pascal Luttgens.
     *
     * @param assetInputStream
     *
     * @return un AudioAsset.
     */
    @Override
    public Asset LoadFromStream(AssetInputStreamProvider.InputStreamWithMime assetInputStream) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(assetInputStream.getInputStream()));
            AudioFormat af = ais.getFormat();
            int size = (int) (af.getFrameSize() * ais.getFrameLength());
            byte[] audio = new byte[size];
            DataLine.Info info = new DataLine.Info(Clip.class, af, size);
            ais.read(audio, 0, size);

            return new AudioAsset(audio, af, info);
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(AudioAssetLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Impossible de creer un asset à partir du stream.");
        }
    }
}
