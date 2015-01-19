package com.gamefactory.assets.providers;

import com.gamefactory.assets.assetmanager.AssetInputStreamProvider;
import com.gamefactory.assets.assetmanager.AssetKey;
import com.gamefactory.services.ServiceLocator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 * Permet de charger un InputStreamWithMime à partir d'un asset stocké sous la
 * forme d'un fichier.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class FileProvider implements AssetInputStreamProvider {

    private static String assetLocation;

    static {
        try {
            assetLocation = ServiceLocator.getConfig().getJSONObject("assets").getString("directory");
        } catch (JSONException ex) {
            Logger.getLogger(FileProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public InputStreamWithMime getInputStream(AssetKey assetKey) {

        File file;
        try {
            file = new File(assetLocation + assetKey.getType() + "/" + assetKey.getName());
            return new InputStreamWithMime(new FileInputStream(file), assetKey.getExtension());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
