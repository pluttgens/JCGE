/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.graphicengine;

import com.gamefactory.assets.types.ImageAsset;
import com.gamefactory.services.ServiceLocator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * La classe TileSheet représente une grille de tile.
 * 
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class TileSheet {

    private final BufferedImage im;
    private final int width;

    public TileSheet(String name) {
        this.im = loadTileSheet(name);    
        this.width = im.getWidth()/TileEngine.TILE_WIDTH;
    }

    private BufferedImage loadTileSheet(String name) {
        final ImageAsset tileAsset = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", name);

        try {
            return ImageIO.read(new ByteArrayInputStream(tileAsset.getPixels()));
        } catch (IOException ex) {
            throw new RuntimeException("Erreur lors du chargement de la TileSheet : " + name);
        }
    }
    
    public BufferedImage loadTile(int position) {
    	Coord2D c = Coord2D.convCoord(this.width, position);
        return im.getSubimage(c.getX() * TileEngine.TILE_HEIGHT, c.getY()*TileEngine.TILE_WIDTH, 32, 32);
    }

}
