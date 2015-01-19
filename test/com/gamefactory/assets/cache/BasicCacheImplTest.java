/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.cache;

import com.gamefactory.assets.assetmanager.Asset;
import com.gamefactory.assets.assetmanager.AssetKey;
import com.gamefactory.assets.types.AudioAsset;
import com.gamefactory.assets.types.TileAsset;
import javax.sound.sampled.AudioFormat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author scalpa
 */
public class BasicCacheImplTest {

    @Test
    public void test() {
        BasicCacheImpl cache = new BasicCacheImpl();
        AssetKey key = new AssetKey("type", "name.ext");
        Asset asset = new TileAsset(new byte[10], "ext");
        cache.addToCache(key, asset);

        assertTrue(asset.equals(cache.getFromCache(key)));
        assertTrue(asset == cache.getFromCache(key));

        assertTrue(asset.equals(cache.getCopyFromCache(key)));
        assertFalse(asset == cache.getCopyFromCache(key));

        assertTrue(cache.removeFromCache(key));
        assertFalse(cache.removeFromCache(key));
        assertEquals(null, cache.getFromCache(key));
        assertEquals(null, cache.getCopyFromCache(key));

        cache.addToCache(key, asset);
        cache.clearCache();

        assertFalse(cache.removeFromCache(key));
        assertEquals(null, cache.getFromCache(key));
        assertEquals(null, cache.getCopyFromCache(key));

    }

}
