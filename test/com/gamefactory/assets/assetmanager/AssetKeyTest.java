/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.assetmanager;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author scalpa
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
