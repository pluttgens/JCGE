/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.assets.types.ImageAsset;
import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.displayable.Component;
import com.gamefactory.services.ServiceLocator;
import java.awt.image.BufferedImage;

/**
 *
 * @author scalpa
 */
public class PlayerFindTreasureScript extends UpdateScript<Component> {
    
    private Position hero;
    private Position tresure;
    private Renderer renderer;
    private BufferedImage image;

    public PlayerFindTreasureScript() {
       
    }
    
    
    
    @Override
    public void init(Component c) {
        super.init(c);
         this.image = ((ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "treasure.png")).getBufferedImage();
        this.hero = (Position) this.owner.getComponentManager().getComponentFromGO("HERO", Position.class);
        this.tresure = (Position) this.owner.getComponentManager().getComponent(Position.class);
        this.renderer = (Renderer) this.owner.getComponentManager().getComponent(Renderer.class);
    }

    @Override
    public void execute() {
        if (hero.distanceWith(tresure) < 10) {
            renderer.setImage(image);
        }
    }
    
    
    
    
}
