/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.displayable.scripts.updatescripts;

import com.gg.jcge.components.Position;
import com.gg.jcge.displayable.Camera;
import com.gg.jcge.displayable.scripts.UpdateScript;
import com.gg.jcge.game.Game;

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
