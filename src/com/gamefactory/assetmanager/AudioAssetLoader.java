/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assetmanager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioAssetLoader implements TypeLoader {

    @Override
    public Object LoadFromStream(InputStream assetInputStream) {
        try {
            BufferedInputStream bis = new BufferedInputStream(assetInputStream);
            
            return AudioSystem.getAudioInputStream(bis);
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(AudioAssetLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
