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
 * @author scalpa
 */
public class PlayerFindTreasureScript extends UpdateScript<ComponentManager> {

    private Position hero;
    private Position tresure;
    private Renderer renderer;
    private BufferedImage image;

    public PlayerFindTreasureScript() {

    }

    @Override
    public void init(ScriptManager script) {
        super.init(script);
        this.image = ((ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "treasure.png")).getBufferedImage();
    }

    @Override
    public void load() {
        this.hero = (Position) this.owner.getOwner().getComponentFromGO("HERO", Position.class);
        this.tresure = (Position) this.owner.getOwner().getComponent(Position.class);
        this.renderer = (Renderer) this.owner.getOwner().getComponent(Renderer.class);
        this.renderer.disable();
    }

    @Override
    public void execute() {
        if (hero.distanceWith(tresure) < 10) {
            renderer.setImage(image);
            renderer.enable();
        }
    }

}
