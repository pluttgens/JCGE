package com.gamefactory.assets.assetmanager;

import com.gamefactory.assets.providers.FileProvider;
import com.gamefactory.assets.cache.BasicCacheImpl;
import com.gamefactory.services.ServiceLocator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 * L'AssetManager est la facade de tout notre systeme de gestion d'asset. C'est
 * lui qui va servir d'interface entre le reste du programme et tous les aspects
 * liés à la gestion des assets.
 *
 * Il contient (pour l'instant) un provider mais on pourrait peut être en avoir
 * plusieurs de même pour le cache et une map de TypeLoaders.
 *
 * Il ne propose que à l'utilisateur de charger un asset, une copie d'un asset
 * ou de vider le cache (pour l'instant).
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class AssetManager {

    private final Object lock = new Object();

    private AssetInputStreamProvider provider;
    private final HashMap<String, TypeLoader> loaders;
    private final AssetCache cache;

    // Doit être vide.
    public AssetManager() {
        this.loaders = new HashMap<>();
        this.cache = new BasicCacheImpl();
        init();
    }

    //PENSER A RAJOUTER DES FACTORY POUR LES PROVIDERS/TYPELOADERS.
    
    /**
     * Initialise le provider et la liste des loaders à partir d'un fichier de
     * configuration.
     *
     * - Pascal Luttgens.
     */
    public final void init() {
        this.provider = new FileProvider();
        JSONObject config = ServiceLocator.getConfig();
        JSONObject loadersConfig = config.getJSONObject("assets").getJSONObject("loaders");
        Iterator<String> it = loadersConfig.keys();
        while (it.hasNext()) {
            String key = it.next();
            try {
                registerType(key, (TypeLoader) Class.forName("com.gamefactory.assets.types." + loadersConfig.getString(key)).newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(AssetManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Asset loadAsset(String type, String name) {
        TypeLoader loader = loaders.get(type);
        AssetInputStreamProvider.InputStreamWithMime assetStream = provider.getInputStream(new AssetKey(type, name));

        return loader.LoadFromStream(assetStream);
    }

    public void registerType(String type, TypeLoader loader) {
        loaders.put(type, loader);
    }

    public Object getAsset(String type, String name) {
        synchronized (lock) {
            Asset asset = cache.getFromCache(new AssetKey(type, name));
            if (asset != null) {
                return asset;
            }
            asset = loadAsset(type, name);
            cache.addToCache(new AssetKey(type, name), asset);
            return asset;
        }
    }

    public Object getAssetCopy(String type, String name) {
        synchronized (lock) {
            Asset asset = cache.getCopyFromCache(new AssetKey(type, name));
            if (asset != null) {
                return asset;
            }
            asset = loadAsset(type, name);
            cache.addToCache(new AssetKey(type, name), asset);
            return cache.getCopyFromCache(new AssetKey(type, name));
        }
    }

    public void clearCache() {
        synchronized (lock) {
            cache.clearCache();
        }
    }

}
