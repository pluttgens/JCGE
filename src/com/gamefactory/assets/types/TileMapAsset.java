/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;

/**
 *
 * @author scalpa
 */
public class TileMapAsset extends Asset {

    private List<Tile> tiles;
    
    @Override
    public Asset clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
