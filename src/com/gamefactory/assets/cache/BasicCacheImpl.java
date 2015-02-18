package com.gamefactory.assets.cache;

import com.gamefactory.assets.assetmanager.Asset;
import com.gamefactory.assets.assetmanager.AssetCache;
import com.gamefactory.assets.assetmanager.AssetKey;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ImplÃ©mentation basique de l'interface AssetCache sans aucun comportement
 * prÃ©dÃ©fini pour la supression des assets.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class BasicCacheImpl implements AssetCache {

    private final ConcurrentHashMap<AssetKey, Asset> cache;

    public BasicCacheImpl() {
        this.cache = new ConcurrentHashMap<>();
    }

    /**
     * Ajoute au cache l'asset et sa clé
     */
    @Override
    public void addToCache(AssetKey assetKey, Asset asset) {
        cache.put(assetKey, asset);
    }

    /**
     * Récupère la clé de l'asset à partir du cache
     */
    @Override
    public Asset getFromCache(AssetKey assetKey) {
        return cache.get(assetKey);
    }

    /**
     * Retire la clé de l'asset du cache
     */
    @Override
    public boolean removeFromCache(AssetKey assetKey) {
        return (cache.remove(assetKey) != null);
    }

    /**
     * Vide le cache
     */
    @Override
    public void clearCache() {
        cache.clear();
    }

    /**
     * Récupère une copie de la clé de l'asset du cache
     */
    @Override
    public Asset getCopyFromCache(AssetKey assetKey) {
        Asset copy = cache.get(assetKey);
        return (copy != null) ? copy.clone() : null;
    }

}
