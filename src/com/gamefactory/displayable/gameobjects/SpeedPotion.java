/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable.gameobjects;

import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.displayable.Scene;
import com.gamefactory.scripts.SpeedPotionScript;

/**
 *
 * @author ngo
 */
public class SpeedPotion extends GameObject {
    
    @Override
    public void init(Scene owner) {
        super.init(owner);
        this.componentManager.add(new Position(), new Renderer());
        this.getScriptManager().add(new SpeedPotionScript());
    }
    
}
