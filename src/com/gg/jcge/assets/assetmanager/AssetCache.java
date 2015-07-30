package com.gg.jcge.assets.assetmanager;

/**
 * L'interface AssetCache définit les opérations qu'un cache d'asset doit
 * réaliser. Un cache d'asset est une stratégie de mise et de maintien en
 * mémoire des ressources. Il évite de devoir recharger une ressource lorsque
 * l'on en a besoin plusieurs fois au même moment.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public interface AssetCache {
    /**
     * Ajoute un asset au cache avec la clé permettant de l'identifier.
     *
     * - Pascal Luttgens.
     *
     * @param assetKey
     * @param asset
     */
    void addToCache(AssetKey assetKey, Asset asset);

    /**
     * Récupère un asset du cache à partir de la clé permettant de l'identifier.
     *
     * - Pascal Luttgens.
     *
     * @param assetKey
     *
     * @return
     */
    Asset getFromCache(AssetKey assetKey);

    /**
     * Essaie de récupérer une copie de l'asset à partir de la clé permettant de
     * l'identifier.
     *
     * - Pascal Luttgens.
     *
     * @param assetKey
     *
     * @return
     */
    Asset getCopyFromCache(AssetKey assetKey);

    /**
     * Retire un asset du cache à partir de la clé permettant de l'identifier.
     *
     * - Pascal Luttgens.
     *
     * @param assetKey
     *
     * @return
     */
    boolean removeFromCache(AssetKey assetKey);

    /**
     * Vide le cache.
     *
     * - Pascal Luttgens.
     */
    void clearCache();

}
