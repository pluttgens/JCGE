/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.assetmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scalpa
 */
public abstract class AssetInputStreamProvider {

    protected final static List<String> assetExtentions = new ArrayList<>();

    static {
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(new File("conf/assets.cfg")));
            while (isr.ready()) {
                String ext = new String();
                Character c = (char) isr.read();
                while (!c.equals(',')) {
                    ext += c;
                    c = (char) isr.read();
                }
                assetExtentions.add(ext);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AssetInputStreamProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AssetInputStreamProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public abstract InputStream getInputStream(String type, String name);

}
