/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.displayable.scripts.loadingscripts;

import com.gg.jcge.displayable.Landscape;
import com.gg.jcge.displayable.scripts.LoadingScript;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author ngo
 */
public class LandscapeHorizontalScript extends LoadingScript {
    
    private Landscape landscapeHorizontal;
    private BufferedImage image;
    
    @Override
    public void executeOnce() {
        try {
            image = ImageIO.read(new File("landscapeHorizontal.png"));
        } catch (Exception e) {
            System.out.println("e");
        }
    }
    
    @Override
    public void load() {
        //this.landscapeHorizontal = this.scene.getComponentManager().getLandscape();
    }
    
}
