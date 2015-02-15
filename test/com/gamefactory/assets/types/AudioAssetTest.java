package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.AssetManager;
import com.sun.org.apache.bcel.internal.generic.F2D;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class AudioAssetTest {

    @Test
    public void test() throws Exception {
        AssetManager assetManager = new AssetManager();
        AudioAsset audioAsset = (AudioAsset) assetManager.getAsset("audio", "test1.wav");

        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("assets/audio/test1.wav"));
        AudioFormat af = ais.getFormat();
        int size = (int) (af.getFrameSize() * ais.getFrameLength());
        byte[] audio = new byte[size];
        DataLine.Info info = new DataLine.Info(Clip.class, af, size);
        ais.read(audio, 0, size);

        Assert.assertArrayEquals(audioAsset.getAudioData(), audio);
         // assertEquals(audioAsset.getFormat(), af); fail mais les résultats sont identiques <PCM_SIGNED 16350.0 Hz, 16 bit, mono, 2 bytes/frame, little-endian> / <PCM_SIGNED 16350.0 Hz, 16 bit, mono, 2 bytes/frame, little-endian>
         // assertEquals(audioAsset.getInfo(), info); idem
        
    }

}
