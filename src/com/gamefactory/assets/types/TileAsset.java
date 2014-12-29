/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.types;

import com.gamefactory.assets.assetmanager.Asset;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author scalpa
 */
public class TileAsset extends Asset {

    private byte[] pixels;
    private String extention;

    public TileAsset(byte[] pixels, String extention) {
        this.pixels = pixels;
        this.extention = extention;
    }

    public byte[] getPixels() {
        return pixels;
    }

    public String getExtention() {
        return extention;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.hashCode(this.pixels);
        hash = 97 * hash + Objects.hashCode(this.extention);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TileAsset other = (TileAsset) obj;
        if (!Arrays.equals(this.pixels, other.pixels)) {
            return false;
        }
        return true;
    }

    
    @Override
    public Asset clone() {
        return new TileAsset(Arrays.copyOf(pixels, pixels.length), extention);
    }

}
