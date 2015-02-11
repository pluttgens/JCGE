/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;
import com.gamefactory.assets.assetmanager.AssetInputStreamProvider;
import com.gamefactory.assets.assetmanager.TypeLoader;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * La classe ImageAssetLoader permet de créer un ImageAsset optimisé pour son
 stockage et son utilisation pour le moteur à paritr d'un InputStreamWithMime.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ImageAssetLoader implements TypeLoader {

    @Override
    public Asset LoadFromStream(AssetInputStreamProvider.InputStreamWithMime assetInputStream) {
        try {

            BufferedImage img = ImageIO.read(assetInputStream.getInputStream());
            byte[] iminbyte;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(img, assetInputStream.getMime(), baos);
                baos.flush();
                iminbyte = baos.toByteArray();
            }
            return new ImageAsset(iminbyte, assetInputStream.getMime());
        } catch (IOException ex) {
            Logger.getLogger(ImageAssetLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Impossible de creer un asset à partir du stream.");
        }
    }
}
