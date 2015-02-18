/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.Script;
import com.gamefactory.game.Game;
/**
 *
 * @author Adrien
 */
public class TreasurePositionScript extends Script{
    private Position position;

    @Override
    public void init(ComponentManager owner) {
        this.owner = owner;
        this.position = (Position) this.owner.getComponent(Position.class);
        position.setX((int)(Math.random() * Game.WIDTH));
        position.setY((int)(Math.random() * Game.HEIGHT));
    }
}
