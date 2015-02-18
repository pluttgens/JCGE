/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.Script;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.game.Game;
import com.gamefactory.services.ServiceLocator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author scalpa
 */
public class PlayerInputHandler extends Script implements KeyListener {

    private final static int NB_KEYS = Short.MAX_VALUE;

    private Position position;
    private boolean[] keys;

    public PlayerInputHandler() {
        super();
        this.keys = new boolean[NB_KEYS];
    }

    @Override
    public void init(ComponentManager owner) {
        this.owner = owner;
        this.position = (Position) owner.getComponent(Position.class);
        this.position.setX(Game.WIDTH / 2 - 20);
        this.position.setY(Game.HEIGHT / 2 - 20);
        ServiceLocator.getGameWindow().getFrame().addKeyListener(this);
        ServiceLocator.getGameWindow().getCanvas().addKeyListener(this);
    }

    private boolean isDirectionalArrow(int key) {
        return (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP);
    }

    private void resetDirectionalArrow() {
        this.keys[KeyEvent.VK_LEFT] = false;
        this.keys[KeyEvent.VK_RIGHT] = false;
        this.keys[KeyEvent.VK_DOWN] = false;
        this.keys[KeyEvent.VK_UP] = false;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (isDirectionalArrow(key)) {
            resetDirectionalArrow();
            this.keys[key] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        this.keys[ke.getKeyCode()] = false;
    }

    @Override
    public void update() {
        if (this.keys[KeyEvent.VK_LEFT]) {
            position.setOrientation(Position.Orientation.LEFT);
            position.setxVelocity(-1f);
        } else {
            if (position.getxVelocity() < 0) {
                position.setxVelocity(0);
            }
        }
        if (this.keys[KeyEvent.VK_RIGHT]) {
            position.setOrientation(Position.Orientation.RIGHT);
            position.setxVelocity(1f);
        } else {
            if (position.getxVelocity() > 0) {
                position.setxVelocity(0);
            }
        }
        if (this.keys[KeyEvent.VK_UP]) {
            position.setOrientation(Position.Orientation.UP);
            position.setyVelocity(-1f);
        } else {
            if (position.getyVelocity() < 0) {
                position.setyVelocity(0);
            }
        }
        if (this.keys[KeyEvent.VK_DOWN]) {
            position.setOrientation(Position.Orientation.DOWN);
            position.setyVelocity(1f);
        } else {
            if (position.getyVelocity() > 0) {
                position.setyVelocity(0);
            }
        }
    }
}
