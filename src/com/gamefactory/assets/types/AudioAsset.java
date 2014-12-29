/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;
import java.util.Arrays;
import java.util.Objects;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;

/**
 *
 * @author scalpa
 */
public class AudioAsset extends Asset {

    private byte[] audioData;
    private AudioFormat af;
    private DataLine.Info info;

    public AudioAsset(byte[] audioData, AudioFormat af, DataLine.Info info) {
        this.audioData = audioData;
        this.af = af;
        this.info = info;
    }

    public byte[] getAudioData() {
        return audioData;
    }

    public AudioFormat getFormat() {
        return af;
    }

    public DataLine.Info getInfo() {
        return info;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Arrays.hashCode(this.audioData);
        hash = 37 * hash + Objects.hashCode(this.af);
        hash = 37 * hash + Objects.hashCode(this.info);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AudioAsset other = (AudioAsset) obj;
        if (!Arrays.equals(this.audioData, other.audioData)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public Asset clone() {
        return new AudioAsset(Arrays.copyOf(audioData, audioData.length), af, info);
    }

}
