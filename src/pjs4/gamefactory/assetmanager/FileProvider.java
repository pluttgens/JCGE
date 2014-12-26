/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.assetmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scalpa
 */
public class FileProvider extends AssetInputStreamProvider {

    private final static String assetLocation = "assets/";

    @Override
    public InputStream getInputStream(String type, String name) {

        File file;
        for (String assetExtention : assetExtentions) {
            try {
                System.out.println(assetLocation + type + "/" + name + "." + assetExtention);
                file = new File(assetLocation + type + "/" + name + "." + assetExtention);
                return new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

}
