/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.listeners;

import com.gamefactory.components.Position;

public class BlockOnCollisionListener extends ComponentListener<Position, Void> {
    
    @Override
    public void init(Position p) {
        super.init(p);
    }

    @Override
    public void onEvent(Void event) {
        owner.setxVelocity(0);
        owner.setyVelocity(0);
    }   
    
}
