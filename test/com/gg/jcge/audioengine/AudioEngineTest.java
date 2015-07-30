package com.gg.jcge.audioengine;

import com.gg.jcge.assets.assetmanager.AssetManager;
import com.gg.jcge.services.ServiceLocator;
import org.junit.Test;

/**
 *
 * @author Pascal Luttgens
 */
public class AudioEngineTest {

    @Test
    public void test() {
        ServiceLocator.provideAssetManager(new AssetManager());
        AudioEngine ae = new AudioEngine();

        ae.playSound("test1.wav", null);
        
        
        
    }

}
