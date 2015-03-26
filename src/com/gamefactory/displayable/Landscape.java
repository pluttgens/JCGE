package com.gamefactory.displayable;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.gamefactory.game.Displayable;
import com.gamefactory.graphicengine.Tile;
import java.awt.Rectangle;
import java.util.Arrays;

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
        this.width = 4000;
        this.height = 4000;
        
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
    public void load() {
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRenderedArea() {
        return renderedArea;
    }

    public void addTiles(Tile ... tiles) {
        this.tiles.addAll(Arrays.asList(tiles));
    }
    
}
