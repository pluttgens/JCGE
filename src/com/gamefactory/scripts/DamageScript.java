/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.assets.types.ImageAsset;
import com.gamefactory.components.Health;
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
public class DamageScript extends UpdateScript<ComponentManager> {
    
    private Position hero;
    private Health healthPoint;
    private Position projectile;
    private Renderer renderer;
    private BufferedImage image;
    
    public DamageScript() {

    }

    @Override
    public void init(ScriptManager script) {
        super.init(script);
        this.image = ((ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "fireball.png")).getBufferedImage();
    }

    @Override
    public void load() {
        this.hero = (Position) this.owner.getOwner().getComponentFromGO("HERO", Position.class);
        this.projectile = (Position) this.owner.getOwner().getComponent(Position.class);
        this.renderer = (Renderer) this.owner.getOwner().getComponent(Renderer.class);
    }

    @Override
    public void execute() {
        renderer.setImage(image);
        if(hero.distanceWith(projectile) < 10) {
            healthPoint.sub(1);
            renderer.disable();
        }
    }
    
}
