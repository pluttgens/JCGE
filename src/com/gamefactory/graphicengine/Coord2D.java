/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Pascal Luttgens 201
 */
package com.gamefactory.graphicengine;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * <p>
 * <b>Coord2D</b> est la classe permettant la gestion de coordonnées 2D
 * positives. Elle permet de simplifier l'utilisation des données renseignant la
 * position d'un élément dans le plan en utlisant des couples de coordonnées
 * (x,y) tout en proposant une fonction de conversion permettant de retourner
 * une coordonnée simple pour un tableau 1D.</p>
 *
 * @author Le Victor
 * @author Luttgens Pascal
 * @version 2.0
 * @since 1.0
 */
public class Coord2D {

    /**
     * Coordonnée en abscisse. Doit être supérieure ou égale à 0.
     *
     * @see #Coord(int, int)
     * @see #getX()
     * @see #setX(int)
     * @see #convCoord(int)
     *
     * @since 1.0
     */
    private int x;

    /**
     * Coordonnée en ordonnée. Doit être supérieure ou égale à 0.
     *
     * @see #Coord(int, int)
     * @see #getY()
     * @see #setY(int)
     * @see #convCoord(int)
     *
     * @since 1.0
     */
    private int y;

    /**
     * Crée un couple de coordonnées à partir d'un couple donné en paramètre.
     * Les coordonnées ne peuvent être négative.
     *
     * @param x coordonnée en abscisse
     * @param y coordonnée en ordonnée
     *
     * @throws IllegalArgumentException Si les cordonnées sont négatives
     *
     * @see #x
     * @see #y
     *
     * @since 1.0
     */
    public Coord2D(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be positive.");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la coordonnée en abscisse
     *
     * @return coordonnée de l'objet en abscisse
     *
     * @see #x
     *
     * @since 1.0
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retourne la coordonnée en ordonnée
     *
     * @return coordonnée de l'objet en ordonnée
     *
     * @see #y
     *
     * @since 1.0
     */
    public int getY() {
        return this.y;
    }

    /**
     * Modifie la coordonnée en abscisse
     *
     * @param x coordonnée en abscisse
     *
     * @throws IllegalArgumentException Si x est strictement inférieur à 0
     *
     * @see #x
     *
     * @since 1.0
     */
    public void setX(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Coordinate must be postive");
        }
        this.x = x;
    }

    /**
     * Modifie la coordonnée en ordonnée
     *
     * @param y coordonnée en ordonnée
     *
     * @throws IllegalArgumentException Si y est strictement inférieur à 0
     *
     * @see #y
     *
     * @since 1.0
     */
    public void setY(int y) {
        if (y < 0) {
            throw new IllegalArgumentException("Coordinate must be postive");
        }
        this.y = y;
    }

    /**
     * <p>
     * Convertit le couple de coordonée représenté par l'objet en coordonnée
     * simple en fonction de la largeur du plan. ( Sur une grille de 3x3, en
     * parcourant la grille de ligne en ligne du haut vers le bas, la coordonnée
     * de la première case (0,0) = 0, (1,0) = 1, (2,0) = 2 ; on passe à la
     * deuxième ligne, (0,1) = 3, (1,1) = 4, (2,1) = 5 ... ).</p>
     *
     *
     * @param ratio largeur du plan qui doit être supérieur à 1.
     *
     * @return coordonnée en 1D
     *
     * @throws IllegalArgumentException Si ratio &lt;= 1
     *
     * @see #x
     * @see #y
     *
     * @since 1.0
     */
    public Integer convCoord(int ratio) {
        if (ratio <= 1) {
            throw new IllegalArgumentException("Width must be strictly superior to 1");
        }
        return this.y * ratio + this.x;
    }

    /**
     * <p>
     * Convertit l'indice donné en paramètre en couple de coordonnées 2D en
     * fonction de la largeur du plan. ( Sur une grille de 3x3, en parcourant la
     * grille de ligne en ligne du haut vers le bas, la coordonnée de la
     * première case (0,0) = 0, (1,0) = 1, (2,0) = 2 ; on passe à la deuxième
     * ligne, (0,1) = 3, (1,1) = 4, (2,1) = 5 ... ).</p>
     *
     *
     * @param ratio largeur du plan qui doit être supérieur à 1.
     * @param pos   indice de la position dans le tableau 1D
     *
     * @return coordonnée en 2D
     *
     * @throws IllegalArgumentException Si ratio &lt;= 1
     *
     * @see #x
     * @see #y
     *
     * @since 1.1
     */
    public static Coord2D convCoord(int ratio, int pos) {
        if (ratio <= 1) {
            throw new IllegalArgumentException("Width must be strictly superior to 1");
        }
        return new Coord2D(pos % ratio, pos / ratio);
    }
    
    public boolean isBetween(Coord2D c1, Coord2D c2) {
        int c2x = c2.getX() - c1.getX();
        int c2y = c2.getY() - c1.getY();
        Rectangle r = new Rectangle(c1.getX(), c1.getY(), c2x < 0 ? -c2x : c2x, c2y < 0 ? -c2y : c2y);
        return r.contains(new Point(this.x, this.y));
    }

}
