/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assetmanager;

import java.io.InputStream;
import java.util.HashMap;

/**
 *
 * @author scalpa
 */
public class AssetManager {

    private final Object lock = new Object();

    private AssetInputStreamProvider provider;
    private final HashMap<String, TypeLoader> loaders;
    private HashMap<String, Object> assets;

    public AssetManager(AssetInputStreamProvider provider) {
        this.provider = provider;
        this.loaders = new HashMap<>();
        this.assets = new HashMap<>();
    }

    private Object loadAsset(String type, String name) {
        TypeLoader loader = loaders.get(type);
        InputStream assetStream = provider.getInputStream(type, name);

        return loader.LoadFromStream(assetStream);
    }

    public void registerType(String type, TypeLoader loader) {
        loaders.put(type, loader);
    }

    public Object getAsset(String type, String name) {
        synchronized (lock) {
            if (assets.containsKey(name)) {
                return assets.get(name);
            }
            Object asset = loadAsset(type, name);
            assets.put(name, asset);
            return asset;
        }
    }

    public void clearCache() {
        synchronized (lock) {
            assets.clear();
        }
    }

}
