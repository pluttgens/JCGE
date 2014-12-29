/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.assets.assetmanager;

import java.io.File;
import java.io.FileDescriptor;
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

    public class DecoratedInputStream{
        private final InputStream inputStream;

        private final String mime;
        
        public DecoratedInputStream(InputStream inputStream, String mime) {
            this.inputStream = inputStream;
            this.mime = mime;
        }
        
        public InputStream getInputStream() {
            return this.inputStream;
        }
        
        public String getMime() {
            return this.mime;
        }
    }
    
    public abstract DecoratedInputStream getInputStream(AssetKey assetKey);

}
