/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.Camera;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.Scene;
import com.gamefactory.game.Game;

/**
 *
 * @author scalpa
 */
public class GameObjectCameraScript extends UpdateScript<ComponentManager> {

    private Camera c;

    private Position focus;

    @Override
    public void load() {
        this.c = this.owner.getOwner().getScene().getCamera();
        this.focus = (Position) this.owner.getOwner().getComponentFromGO("HERO", Position.class);
    }

    @Override
    public void execute() {
        c.setX((int) (focus.getX() - (Game.WIDTH / 2)));
        c.setY((int) (focus.getY() - (Game.HEIGHT / 2)));
    }

}
