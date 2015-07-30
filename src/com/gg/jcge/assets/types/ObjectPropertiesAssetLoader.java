/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.assets.types;

import com.gg.jcge.assets.assetmanager.Asset;
import com.gg.jcge.assets.assetmanager.AssetInputStreamProvider;
import com.gg.jcge.assets.assetmanager.TypeLoader;
import com.gg.jcge.services.ServiceLocator;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author scalpa
 */
public class ObjectPropertiesAssetLoader implements TypeLoader {

    @Override
    public Asset LoadFromStream(AssetInputStreamProvider.InputStreamWithMime assetInputStream) {
        String json = new String();
        java.util.Scanner s = new java.util.Scanner(assetInputStream.getInputStream()).useDelimiter("\\A");
        while (s.hasNext()) {
            json += s.nextLine();
        }
        JSONObject jsono = ServiceLocator.getJSONObject(json);

        Map<String, Map<String, String>> objectProperties = new HashMap<>();
        Iterator<String> it = jsono.keys();
        while (it.hasNext()) {
            String key = it.next();
            JSONObject jsono2 = jsono.getJSONObject(key);
            Iterator<String> it2 = jsono2.keys();
            Map<String, String> values = new HashMap<>();
            while (it2.hasNext()) {
                String key2 = it2.next();
                String value = jsono2.getString(key2);
                values.put(key2, value);
            }
            objectProperties.put(key, values);
        }

        return new ObjectPropertiesAsset(objectProperties);
    }

}
