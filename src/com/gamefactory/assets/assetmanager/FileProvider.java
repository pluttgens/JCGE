/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.assetmanager;

import com.gamefactory.services.ServiceLocator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pascal Luttgens
 */
public class FileProvider extends AssetInputStreamProvider {

    private static String assetLocation;

    static {
        try {
            assetLocation = ServiceLocator.getConfig().getJSONObject("assets").getString("directory");
        } catch (JSONException ex) {
            Logger.getLogger(FileProvider.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public DecoratedInputStream getInputStream(AssetKey assetKey) {

        File file;
        try {
            file = new File(assetLocation + assetKey.getType() + "/" + assetKey.getName());
            return new DecoratedInputStream(new FileInputStream(file), assetKey.getExtension());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileProvider.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return null;
    }

}
