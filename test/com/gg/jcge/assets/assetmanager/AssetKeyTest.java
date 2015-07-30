package com.gg.jcge.assets.assetmanager;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Pascal Luttgens
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public class AssetKeyTest {
    
    @Test
    public void test() {
        AssetKey assetKey = new AssetKey("type", "name.ext");
        
        assertEquals("type", assetKey.getType());
        assertEquals("name.ext", assetKey.getName());
        assertEquals("ext", assetKey.getExtension());
    }
    
}
