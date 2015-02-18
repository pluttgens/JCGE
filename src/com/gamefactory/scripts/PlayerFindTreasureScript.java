/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.assets.types.ImageAsset;
import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.Script;
import com.gamefactory.services.ServiceLocator;
import java.awt.image.BufferedImage;

/**
 *
 * @author scalpa
 */
public class PlayerFindTreasureScript extends Script {
    
    private Position hero;
    private Position tresure;
    private Renderer renderer;
    private BufferedImage image;

    public PlayerFindTreasureScript() {
        this.image = ((ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "treasure.png")).getBufferedImage();
    }
    
    
    
    @Override
    public void init(ComponentManager owner) {
        super.init(owner); //To change body of generated methods, choose Tools | Templates.
        this.hero = (Position) this.owner.getComponentFromGO("HERO", Position.class);
        this.tresure = (Position) this.owner.getComponent(Position.class);
        this.renderer = (Renderer) this.owner.getComponent(Renderer.class);
    }

    @Override
    public void update() {
        if (hero.distanceWith(tresure) < 10) {
            renderer.setImage(image);
        }
    }
    
    
    
    
}
