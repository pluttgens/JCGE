package com.gg.jcge.assets.assetmanager;

/**
 * Superclass représentant tous les assets du jeu. Un asset est tout simplement
 * une resource extérieure utilisée pas le moteur (image, son, model...).
 * 
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public abstract class Asset implements Cloneable {

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract Asset clone();
}
