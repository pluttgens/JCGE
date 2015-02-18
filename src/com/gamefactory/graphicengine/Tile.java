package com.gamefactory.graphicengine;

import java.awt.image.BufferedImage;

public class Tile {
	
	private final BufferedImage im;
	private Coord2D coord;
	
	public Tile(BufferedImage i, Coord2D c) { 		
		 im = i ;	 
		 coord = c ;
    }
	
	/**
	 * Recupere la coordonnee d'un bloc (image correspondant au fond)
	 * @return
	 */
	public Coord2D getCoord(){
		return coord ;
		
	}
	
	/**
	 * Recupere la coordonnee x en abscisse
	 * @return
	 */
	public int getX(){
		return coord.getX();
	}
	
	/**
	 * Recupere la coordonnee y en ordonnee
	 * @return
	 */
	public int getY(){
		return coord.getY();
	}
	
	/**
	 * Recupere l'image
	 * @return
	 */
	public BufferedImage getImage(){
		return im ;
	}
}
