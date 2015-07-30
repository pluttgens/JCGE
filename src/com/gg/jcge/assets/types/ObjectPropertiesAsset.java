/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.assets.types;

import com.gg.jcge.assets.assetmanager.Asset;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author scalpa
 */
public class ObjectPropertiesAsset extends Asset {

    private final Map<String, Map<String, String>> properties;

    public ObjectPropertiesAsset(Map<String, Map<String, String>> properties) {
        this.properties = properties;
    }

    public Map<String, String> retrieve(String key) {
        return properties.get(key);
    }
    
    @Override
    public Asset clone() {
        Map<String, Map<String, String>> clonedProperties = new HashMap<>(properties);
        ObjectPropertiesAsset objectPropertiesAsset = new ObjectPropertiesAsset(clonedProperties);
        return objectPropertiesAsset;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.properties);
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
        final ObjectPropertiesAsset other = (ObjectPropertiesAsset) obj;
        if (!Objects.equals(this.properties, other.properties)) {
            return false;
        }
        return true;
    }

}
