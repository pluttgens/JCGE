/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.ScriptManager;

/**
 *
 * @author ngo
 */
public class DamageScript extends UpdateScript<ComponentManager> {
    
    private Position hero;
    
    public DamageScript() {

    }

    @Override
    public void init(ScriptManager script) {
        super.init(script);
    }

    @Override
    public void load() {
        this.hero = (Position) this.owner.getOwner().getComponentFromGO("HERO", Position.class);
    }

    @Override
    public void execute() {
        
    }
    
}
