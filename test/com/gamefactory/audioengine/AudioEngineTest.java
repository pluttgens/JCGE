package com.gamefactory.audioengine;

import com.gamefactory.assets.assetmanager.AssetManager;
import com.gamefactory.services.ServiceLocator;
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
