package com.gamefactory.graphicengine;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {

    private final BufferedImage im;
    private Point coord;

    public Tile(BufferedImage i, Point coord) {
        this.im = i;
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
    public BufferedImage getImage() {
        return im;
    }

    public boolean isInside(Rectangle rectangle) {
        return rectangle.contains(coord);
    }
}
