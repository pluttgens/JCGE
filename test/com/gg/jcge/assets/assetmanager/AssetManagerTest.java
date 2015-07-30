package com.gg.jcge.assets.assetmanager;

import com.gg.jcge.assets.providers.FileProvider;
import com.gg.jcge.assets.types.AudioAssetLoader;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
