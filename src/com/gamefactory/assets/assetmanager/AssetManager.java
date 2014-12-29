/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.assetmanager;

import com.gamefactory.assets.cache.BasicCacheImpl;
import com.gamefactory.services.ServiceLocator;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author scalpa
 */
public class AssetManager {

    private final Object lock = new Object();

    private AssetInputStreamProvider provider;
    private final HashMap<String, TypeLoader> loaders;
    private final AssetCache cache;


    public AssetManager() {
        this.loaders = new HashMap<>();
        this.cache = new BasicCacheImpl();
        init();
    }

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
        AssetInputStreamProvider.DecoratedInputStream assetStream = provider.getInputStream(new AssetKey(type, name));

        return loader.LoadFromStream(assetStream);
    }

    public void registerType(String type, TypeLoader loader) {
        loaders.put(type, loader);
    }

    public Object getAsset(String type, String name) {
        synchronized (lock) {
            Asset asset = cache.getFromCache(new AssetKey(type, name));
            if ( asset != null) {
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
            if ( asset != null) {
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
