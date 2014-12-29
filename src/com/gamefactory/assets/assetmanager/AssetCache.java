/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.assetmanager;

/**
 *
 * @author scalpa
 */
public interface AssetCache {

    public void addToCache(AssetKey assetKey, Asset asset);

    public Asset getFromCache(AssetKey assetKey);
    
    public Asset getCopyFromCache(AssetKey assetKey);

    public boolean removeFromCache(AssetKey assetKey);

    public void clearCache();

}
