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
public interface TypeLoader {
    
    public Asset LoadFromStream (AssetInputStreamProvider.DecoratedInputStream assetInputStream);
    
}
