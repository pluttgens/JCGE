package com.gg.jcge.displayable;

import com.gg.jcge.game.Displayable;
import com.gg.jcge.graphicengine.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Un landscape représente le décor d'une scène.
 *
 * @author Pascal Luttgens
 *
 * @version 2.0
 *
 * @since 1.0
 */
public class Landscape implements Displayable<Scene> {

    private Scene owner;
    private int width;
    private int height;

    private Rectangle renderedArea;

    private List<Tile> tiles;

    public Landscape() {
        this.renderedArea = new Rectangle();
        this.tiles = new ArrayList<>();
    }

    @Override
    public void init(Scene owner) {
        this.owner = owner;

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
            if (tile.isInside(renderedArea)) {
                g.drawImage(tile.getImage(), (int) (tile.getX() - renderedArea.getX()), (int) (tile.getY() - renderedArea.getY()), null);
            }
        }
    }

    @Override
    public void load() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getRenderedArea() {
        return renderedArea;
    }

    public void setRenderedArea(Rectangle rectangle) {
        this.renderedArea.setBounds(rectangle);
    }

    public void addTiles(Tile... tiles) {
        this.tiles.addAll(Arrays.asList(tiles));
    }

    public void addTiles(List<Tile> tiles) {
        this.tiles.addAll(tiles);
    }

}
