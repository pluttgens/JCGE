package com.gamefactory.displayable;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.gamefactory.game.Displayable;
import com.gamefactory.graphicengine.Coord2D;
import com.gamefactory.graphicengine.Tile;
import com.gamefactory.graphicengine.TileSheet;
import java.awt.Point;
import java.awt.Rectangle;

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

    private final Rectangle renderedArea;

    private final List<Tile> tiles;

    public Landscape() {
        this.width = 2000;
        this.height = 2000;

        tiles = new ArrayList<>();
        this.renderedArea = new Rectangle();
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    @Override
    public void update() {
    }

    public void setRenderedArea(Rectangle rectangle) {
        this.renderedArea.setBounds(rectangle);
    }

    @Override
    public void render(Graphics g) {
        for (Tile tile : tiles) {
            if (tile.isInside(renderedArea))  {
                g.drawImage(tile.getImage(), (int) (tile.getX() - renderedArea.getX()), (int) (tile.getY() - renderedArea.getY()), null);
            }
        }
    }

    @Override
    public void init() {
        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(1), new Point(200,200)));

        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(0), new Point(232, 200)));

        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(0), new Point(200, 232)));

        tiles.add(new Tile(new TileSheet("tileset.png").loadTile(1), new Point(232, 232)));
    }

}
