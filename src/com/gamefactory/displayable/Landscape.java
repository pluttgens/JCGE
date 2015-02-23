package com.gamefactory.displayable;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.gamefactory.game.Displayable;
import com.gamefactory.graphicengine.Coord2D;
import com.gamefactory.graphicengine.Tile;
import com.gamefactory.graphicengine.TileSheet;

public class Landscape implements Displayable {

    private final int width;
    private final int height;

    private final List<Tile> tiles;

    public Landscape() {
        this.width = 2000;
        this.height = 2000;

        tiles = new ArrayList<Tile>();

    }

    public List<Tile> getTiles() {
        return tiles;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        for (Tile tile : tiles) {
            g.drawImage(tile.getImage(), tile.getX(), tile.getY(), null);
        }
    }

    @Override
    public void init() {
        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(1), new Coord2D(0, 0)));

        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(0), new Coord2D(32, 32)));

        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(0), new Coord2D(0, 32)));

        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(1), new Coord2D(32, 0)));
    }


}
