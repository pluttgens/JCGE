/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.ScriptManager;
import com.gamefactory.utils.events.Event;

public class BlockOnCollisionListener extends ListenerScript<ComponentManager> {
    
    private Position position;
    
    @Override
    public void init(ScriptManager sm) {
        super.init(sm);
    }

    @Override
    public void onEvent(Event event) {
        System.out.println("collision");
        position.setxVelocity(0);
        position.setyVelocity(0);
    }   

    @Override
    public void load() {
        this.position = (Position)owner.getOwner().getComponent(Position.class);
    }
    
}
