package com.gamefactory.graphicengine;

import java.awt.image.BufferedImage;


public class Tile {
	private final BufferedImage im;
	private Coord2D coord;
	public Tile(BufferedImage i, Coord2D c) { 		
		 im = i ;	 
		 coord = c ;
    }
	
	public Coord2D getCoord(){
		return coord ;
		
	}
	
	public int getX(){
		return coord.getX();
	}
	
	public int getY(){
		return coord.getY();
	}
	
	public BufferedImage getImage(){
		return im ;
		
	}
}
