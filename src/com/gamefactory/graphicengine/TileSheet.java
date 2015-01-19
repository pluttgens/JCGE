/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.graphicengine;

import com.gamefactory.assets.types.TileAsset;
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

    public TileSheet(String name) {
        im = loadTileSheet(name);
    }

    private BufferedImage loadTileSheet(String name) {
        final TileAsset tileAsset = (TileAsset) ServiceLocator.getAssetManager().getAsset("tiles", name);

        try {
            return ImageIO.read(new ByteArrayInputStream(tileAsset.getPixels()));
        } catch (IOException ex) {
            throw new RuntimeException("Erreur lors du chargement de la TileSheet : " + name);
        }
    }
    
    //A continuer
    public BufferedImage loadTile(int position) {
        return im.getSubimage(0, 0, 32, 32);
    }

}
