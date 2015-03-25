/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.displayable.Landscape;
import com.gamefactory.displayable.Scene;

/**
 *
 * @author ngo
 */
public class LandscapeHorizontalScript extends LoadingScript<Scene> {
    
    private Landscape landscapeHorizontal;
    
    @Override
    public void executeOnce() {
        
    }
    
    @Override
    public void load() {
        this.landscapeHorizontal = this.owner.getOwner().getLandscape();
    }
    
}
