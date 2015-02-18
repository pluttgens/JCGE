package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;
import java.util.Arrays;
import java.util.Objects;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;

/**
 * La classe AudioAsset représente le stockage d'une resource audio.
 * 
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class AudioAsset extends Asset {

    private final byte[] audioData;
    private final AudioFormat af;
    private final DataLine.Info info;

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
        return Arrays.equals(this.audioData, other.audioData);
    }
    
    

    @Override
    public Asset clone() {
        return new AudioAsset(Arrays.copyOf(audioData, audioData.length), af, info);
    }

}
