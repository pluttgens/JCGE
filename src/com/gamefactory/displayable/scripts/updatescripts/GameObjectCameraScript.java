/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable.scripts.updatescripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.Camera;
import com.gamefactory.displayable.scripts.UpdateScript;
import com.gamefactory.game.Game;

/**
 *
 * @author scalpa
 */
public class GameObjectCameraScript extends UpdateScript {

    private Camera c;

    private Position focus;

    @Override
    public void load() {
        this.c = this.scriptManager.getComponentManager().getScene().getCamera();
        this.focus = (Position) this.scriptManager.getComponentManager().getComponent(Position.class);
    }

    @Override
    public void execute() {
        c.setX((focus.getX() - (Game.WIDTH / 2)));
        c.setY((focus.getY() - (Game.HEIGHT / 2)));
    }

}
