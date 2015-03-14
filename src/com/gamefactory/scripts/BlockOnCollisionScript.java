/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.displayable.Script;


public class BlockOnCollisionScript extends Script implements OnCollisionListener {

    private Position position;
    
    @Override
    public void init(ComponentManager owner) {
        super.init(owner);
        this.position = (Position) owner.getComponent(Position.class);
    }

    @Override
    public void onEnterCollision(GameObject go) {
        position.setxVelocity(0);
        position.setyVelocity(0);
    }   
    
}
