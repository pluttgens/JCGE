package com.gamefactory.displayable;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.gamefactory.game.Displayable;
import com.gamefactory.graphicengine.Coord2D;
import com.gamefactory.graphicengine.Tile;
import com.gamefactory.graphicengine.TileSheet;

/**
 * Un landscape représente le décor d'une scène.
 *
 * @author Pascal Luttgens
 *
 * @version 2.0
 *
 * @since 1.0
 */
public class Landscape implements Displayable {

    private final int width;
    private final int height;

    private final Coord2D renderCoord1;
    private final Coord2D renderCoord2;

    private final List<Tile> tiles;

    public Landscape() {
        this.width = 2000;
        this.height = 2000;

        tiles = new ArrayList<Tile>();
        this.renderCoord1 = new Coord2D(0, 0);
        this.renderCoord2 = new Coord2D(0, 0);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    @Override
    public void update() {
    }

    public void setRenderedArea(Coord2D topLeft, Coord2D bottomRight) {
        renderCoord1.setX(topLeft.getX());
        renderCoord1.setY(topLeft.getY());
        renderCoord2.setX(bottomRight.getX());
        renderCoord2.setY(bottomRight.getY());
    }

    @Override
    public void render(Graphics g) {
        for (Tile tile : tiles) {
            if (tile.isBetween(renderCoord1, renderCoord2)) {
                g.drawImage(tile.getImage(), tile.getX(), tile.getY(), null);
            }
        }
    }

    @Override
    public void init() {
        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(1), new Coord2D(300, 0)));

        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(0), new Coord2D(332, 32)));

        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(0), new Coord2D(300, 32)));

        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(1), new Coord2D(332, 0)));
    }

}
