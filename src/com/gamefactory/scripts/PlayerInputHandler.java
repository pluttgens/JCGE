/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.Script;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.services.ServiceLocator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author scalpa
 */
public class PlayerInputHandler extends Script implements KeyListener {

    private Position position;

    public PlayerInputHandler() {
        super();
    }

    @Override
    public void init(ComponentManager owner) {
        this.owner = owner;
        this.position = (Position) owner.getComponent(Position.class);
        ServiceLocator.getWindow().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            position.setOrientation(Position.Orientation.LEFT);
            position.setxVelocity(-1f);
        }
        if (key == KeyEvent.VK_RIGHT) {
            position.setOrientation(Position.Orientation.RIGHT);
            position.setxVelocity(1f);
        }
        if (key == KeyEvent.VK_UP) {
            position.setOrientation(Position.Orientation.UP);
            position.setyVelocity(-1f);
        }
        if (key == KeyEvent.VK_DOWN) {
            position.setOrientation(Position.Orientation.DOWN);
            position.setyVelocity(1f);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            position.setxVelocity(0f);
        }

        if (key == KeyEvent.VK_RIGHT) {
            position.setxVelocity(0f);
        }

        if (key == KeyEvent.VK_UP) {
            position.setyVelocity(0f);
        }

        if (key == KeyEvent.VK_DOWN) {
            position.setyVelocity(0f);
        }
    }

    @Override
    public void update() {

    }
}
