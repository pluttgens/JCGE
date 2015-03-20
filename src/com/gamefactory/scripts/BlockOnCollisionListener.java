/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.ScriptManager;

public class BlockOnCollisionListener extends ListenerScript {

    @Override
    public void init(ScriptManager owner) {
        super.init(owner); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load() {
        
    }

    @Override
    public void onEvent() {
        owner.setxVelocity(0);
        owner.setyVelocity(0);
    }

}
