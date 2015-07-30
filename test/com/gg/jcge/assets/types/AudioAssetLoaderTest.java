package com.gg.jcge.assets.types;

import com.gg.jcge.assets.assetmanager.Asset;
import com.gg.jcge.assets.assetmanager.AssetInputStreamProvider;
import com.gg.jcge.assets.assetmanager.AssetManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioAssetLoaderTest {
    
    public AudioAssetLoaderTest() {
    }

    @Test
    public void test() throws FileNotFoundException {
        AudioAssetLoader audioAssetLoader = new AudioAssetLoader();
        
        Asset asset = audioAssetLoader.LoadFromStream(new AssetInputStreamProvider.InputStreamWithMime(new FileInputStream(new File("assets/audio/test1.wav")), "wav"));
        Assert.assertEquals(asset, new AssetManager().getAsset("audio", "test1.wav"));
    }
    
}
