/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gamefactory.scripts;

import com.gamefactory.assets.types.ImageAsset;
import com.gamefactory.components.Renderer;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.services.ServiceLocator;

/**
 *
 * @author Adrien
 */
public class ObstacleScript extends LoadingScript<ComponentManager>{

    private Renderer ren;
    
    @Override
    public void executeOnce() {
        ren.setImage(((ImageAsset)ServiceLocator.getAssetManager().getAsset("image","stone.png")).getBufferedImage());
    }

    @Override
    public void load() {
        ren = (Renderer)this.owner.getOwner().getComponent(Renderer.class);
    }
    
}
