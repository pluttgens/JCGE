/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.assetmanager;

import com.gamefactory.assets.types.AudioAssetLoader;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pascal Luttgens
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
