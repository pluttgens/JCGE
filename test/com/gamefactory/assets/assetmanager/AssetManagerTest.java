package com.gamefactory.assets.assetmanager;

import com.gamefactory.assets.providers.FileProvider;
import com.gamefactory.assets.types.AudioAssetLoader;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pascal Luttgens
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public class AssetManagerTest {

    @Test
    public void test() {

        AssetManager assetManager = new AssetManager();

        assertTrue(new AudioAssetLoader().LoadFromStream(new FileProvider().getInputStream(new AssetKey("audio", "test1.wav"))).equals(assetManager.getAsset("audio", "test1.wav")));
        assertTrue(assetManager.getAsset("audio", "test1.wav") == assetManager.getAsset("audio", "test1.wav"));

        assertTrue(new AudioAssetLoader().LoadFromStream(new FileProvider().getInputStream(new AssetKey("audio", "test1.wav"))).equals(assetManager.getAssetCopy("audio", "test1.wav")));
        assertFalse(assetManager.getAssetCopy("audio", "test1.wav") == assetManager.getAssetCopy("audio", "test1.wav"));
    }

}
