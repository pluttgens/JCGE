/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;
import com.gamefactory.assets.assetmanager.AssetInputStreamProvider;
import com.gamefactory.assets.assetmanager.TypeLoader;
import com.gamefactory.graphicengine.Tile;
import com.gamefactory.graphicengine.TileEngine;
import com.gamefactory.graphicengine.TileSheet;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scalpa
 */
public class TileMapAssetLoader implements TypeLoader {

    @Override
    public Asset LoadFromStream(AssetInputStreamProvider.InputStreamWithMime assetInputStream) {

        java.util.Scanner s = new java.util.Scanner(assetInputStream.getInputStream()).useDelimiter("\\A");
        List<Tile> tiles = new ArrayList<>();
        TileSheet ts = new TileSheet(s.nextLine());
        int width = 1;
        int height = 1;
        int y = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            for (int i = 0; i < line.length(); ++i) {
                width = i > width ? i : width;
                tiles.add(new Tile(ts, Character.getNumericValue(line.charAt(i)), new Point(i * TileEngine.TILE_WIDTH, y * TileEngine.TILE_HEIGHT)));
            }
            ++height;
            ++y;
        }
        return new TileMapAsset(tiles, width * TileEngine.TILE_WIDTH, height * TileEngine.TILE_HEIGHT);

    }

}
