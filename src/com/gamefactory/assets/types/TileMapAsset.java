/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;
import com.gamefactory.graphicengine.Tile;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author scalpa
 */
public class TileMapAsset extends Asset {

    private List<Tile> tiles;
    private int width;
    private int height;

    public TileMapAsset(List<Tile> tiles, int width, int height) {
        this.tiles = tiles;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public Asset clone() {
        List<Tile> newList = new LinkedList<>();
        Collections.copy(tiles, newList);
        return new TileMapAsset(newList, width, height);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.tiles);
        hash = 71 * hash + this.width;
        hash = 71 * hash + this.height;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TileMapAsset other = (TileMapAsset) obj;
        if (!Objects.equals(this.tiles, other.tiles)) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        return true;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

}
