package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;
import com.gamefactory.assets.assetmanager.AssetInputStreamProvider;
import com.gamefactory.assets.assetmanager.AssetManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

public class AudioAssetLoaderTest {
    
    public AudioAssetLoaderTest() {
    }

    @Test
    public void test() throws FileNotFoundException {
        AudioAssetLoader audioAssetLoader = new AudioAssetLoader();
        
        Asset asset = audioAssetLoader.LoadFromStream(new AssetInputStreamProvider.InputStreamWithMime(new FileInputStream(new File("assets/audio/test1.wav")), "wav"));
        assertEquals(asset, new AssetManager().getAsset("audio", "test1.wav"));
    }
    
}
