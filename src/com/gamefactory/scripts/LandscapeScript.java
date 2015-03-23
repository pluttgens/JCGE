/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.displayable.Landscape;
import com.gamefactory.displayable.Scene;
import com.gamefactory.graphicengine.Tile;
import com.gamefactory.graphicengine.TileSheet;
import java.awt.Point;

/**
 *
 * @author scalpa
 */
public class LandscapeScript extends LoadingScript<Scene> {

    private Landscape landscape;

    @Override
    public void executeOnce() {
        landscape.addTiles(new Tile(new TileSheet("tileset.png").loadTile(1), new Point(200, 200)));

        landscape.addTiles(new Tile(new TileSheet("tileset.png").loadTile(0), new Point(232, 200)));

        landscape.addTiles(new Tile(new TileSheet("tileset.png").loadTile(0), new Point(200, 232)));

        landscape.addTiles(new Tile(new TileSheet("tileset.png").loadTile(1), new Point(232, 232)));
        
        landscape.addTiles(new Tile(new TileSheet("tileset.png").loadTile(2), new Point(168, 200)));

        landscape.addTiles(new Tile(new TileSheet("tileset.png").loadTile(3), new Point(168, 232)));
    }

    @Override
    public void load() {
        this.landscape = this.owner.getOwner().getLandscape();
    }

}
