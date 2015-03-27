package com.gamefactory.graphicengine;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {

    private final TileSheet tileSheet;
    private int tile;
    private Point coord;

    public Tile(TileSheet ts, int tile, Point coord) {
        this.tileSheet = ts;
        this.tile = tile;
        this.coord = coord;
    }

    /**
     * Recupere la coordonnee d'un bloc (image correspondant au fond)
     *
     * @return
     */
    public Point getCoord() {
        return coord;
    }

    /**
     * Recupere la coordonnee x en abscisse
     *
     * @return
     */
    public double getX() {
        return coord.getX();
    }

    /**
     * Recupere la coordonnee y en ordonnee
     *
     * @return
     */
    public double getY() {
        return coord.getY();
    }

    /**
     * Recupere l'image
     *
     * @return
     */
    public TileSheet getTileSheet() {
        return tileSheet;
    }

    public BufferedImage getImage() {
        return tileSheet.loadTile(tile);
    }
    
    public boolean isInside(Rectangle rectangle) {
        return rectangle.contains(coord)
                || rectangle.contains(coord.getX() + TileEngine.TILE_WIDTH, coord.getY())
                || rectangle.contains(coord.getX(), coord.getY() + TileEngine.TILE_HEIGHT)
                || rectangle.contains(coord.getX() + TileEngine.TILE_WIDTH, coord.getY() + TileEngine.TILE_HEIGHT);
    }
}
