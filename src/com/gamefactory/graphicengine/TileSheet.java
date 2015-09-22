package com.gamefactory.graphicengine;

import com.gamefactory.assets.assetmanager.TypeLoader;
import com.gamefactory.assets.types.ImageAsset;
import com.gamefactory.services.ServiceLocator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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

    private final String path;
    private final List<BufferedImage> tiles;

    public TileSheet(String name) {
        this.path = name;
        this.tiles = new LinkedList<>();
        loadTileSheet(name);
    }

    /**
     * Charge la grille de tile
     *
     * @param name
     *
     * @return
     */
    private void loadTileSheet(String name) {
        final ImageAsset tileAsset = (ImageAsset) ServiceLocator.getAssetManager().getAsset(TypeLoader.IMAGE, name);

        try {
            BufferedImage tileSheet = ImageIO.read(new ByteArrayInputStream(tileAsset.getPixels()));
            for (int i = 0; i < (tileSheet.getWidth() / TileEngine.TILE_WIDTH) * (tileSheet.getHeight() / TileEngine.TILE_HEIGHT); ++i) {
                Coord2D c = Coord2D.convCoord((tileSheet.getWidth() / TileEngine.TILE_WIDTH), i);
                tiles.add(tileSheet.getSubimage(c.getX() * TileEngine.TILE_HEIGHT, c.getY() * TileEngine.TILE_WIDTH, 32, 32));
            }
        } catch (IOException ex) {
            throw new RuntimeException("Erreur lors du chargement de la TileSheet : " + name);
        }
    }

    /**
     * Charge les images selon les positions
     *
     * @param position
     *
     * @return
     */
    public BufferedImage loadTile(int position) {
        return tiles.get(position);
    }

}
