/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;
import com.gamefactory.graphicengine.Tile;
import com.gamefactory.graphicengine.TileSheet;
import java.util.List;

/**
 *
 * @author scalpa
 */
public class TileMapAsset extends Asset {

    private List<Tile> tiles;
    
    private TileSheet sheet;
    
    @Override
    public Asset clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Tile> getTiles() {
        
    }
    
}
