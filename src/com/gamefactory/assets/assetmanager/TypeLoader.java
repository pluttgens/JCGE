package com.gamefactory.assets.assetmanager;

/**
 * L'interface TypeLoader permet de charger un asset à partir d'un
 * InputStreamWithMime. En effet en fonction du type d'asset (image, son,
 * texte...), ceux-ci ne seront pas stockée de la même façon dans le cache et 
 * nécessite donc différentes implémentations de la méthode LoadFromStream.
 *
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public interface TypeLoader {

    public Asset LoadFromStream(AssetInputStreamProvider.InputStreamWithMime assetInputStream);

}
