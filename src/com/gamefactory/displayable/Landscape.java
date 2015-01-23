package com.gamefactory.displayable;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.gamefactory.game.Displayable;
import com.gamefactory.graphicengine.Tile;

public class Landscape implements Displayable {
	
	private List<Tile> tiles;
	
	public Landscape(List<Tile> t){
		tiles = t;
	}
	public Landscape(){
		tiles = new ArrayList<Tile>();
	}
	
	public List<Tile> getTiles(){
		return tiles;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		for (Tile tile:tiles){
			g.drawImage(tile.getImage(), tile.getX(), tile.getY(),null);
		}
	}
	
}
