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
import com.gamefactory.displayable.ScriptManager;
import com.gamefactory.services.ServiceLocator;
import java.awt.image.BufferedImage;

/**
 *
 * @author ngo
 */
public class SpeedPotionScript extends UpdateScript<ComponentManager> {
    
    private final static String VELOCITY_KEY = SpeedPotionScript.class.getSimpleName();
    private Position hero;
    private Position speedPotion;
    private Renderer renderer;
    private BufferedImage image;
    
    public SpeedPotionScript() {
    
    }
    
    @Override
    public void init(ScriptManager script) {
        super.init(script);
        this.image = ((ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "SpeedPotion.png")).getBufferedImage();
    }
    
    @Override
    public void load() {
        this.hero = (Position) this.owner.getOwner().getComponentFromGO("HERO", Position.class);
        this.speedPotion = (Position) this.owner.getOwner().getComponent(Position.class);
        this.renderer = (Renderer) this.owner.getOwner().getComponent(Renderer.class);
        this.renderer.setImage(image);
        this.renderer.enable();
    }
    
    @Override
    public void execute() {
        if (hero.distanceWith(speedPotion) < 10) {
            renderer.disable();
            hero.addxVelocityModifiers(VELOCITY_KEY, 50, 5);
            hero.addyVelocityModifiers(VELOCITY_KEY, 50, 5);
        }
    }
    
}
