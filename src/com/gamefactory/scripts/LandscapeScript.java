/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.assets.assetmanager.TypeLoader;
import com.gamefactory.assets.types.TileMapAsset;
import com.gamefactory.displayable.Landscape;
import com.gamefactory.displayable.Scene;
import com.gamefactory.displayable.ScriptManager;
import com.gamefactory.graphicengine.Tile;
import com.gamefactory.graphicengine.TileSheet;
import com.gamefactory.services.ServiceLocator;
import java.awt.Point;
import java.util.Collections;

/**
 *
 * @author scalpa
 */
public class LandscapeScript extends LoadingScript<Scene> {

    private Landscape landscape;
    private String path;

    public void setPath(String path) {
        this.path = path;
    }
    
    
    @Override
    public void init(ScriptManager owner) {
        super.init(owner);
        
    }

    
    @Override
    public void executeOnce() {
        TileMapAsset tma = (TileMapAsset) ServiceLocator.getAssetManager().getAsset(TypeLoader.TILEMAP, "FullTileSet.txt" );
        this.landscape.addTiles(tma.getTiles());
        this.landscape.setHeight(tma.getHeight());
        this.landscape.setWidth(tma.getWidth());
    }

    @Override
    public void load() {
        this.landscape = this.owner.getOwner().getLandscape();
    }

}
