/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.components.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.Script;
import com.gamefactory.displayable.Component;
import com.gamefactory.services.ServiceLocator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author scalpa
 */
public class PlayerInputHandler implements Script, KeyListener {

    private Position position;

    @Override
    public void init(Component component) {
        this.position = (Position) component;
        ServiceLocator.getWindow().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            position.setxVelocity(-1f);
        }
        if (key == KeyEvent.VK_RIGHT) {
            position.setxVelocity(1f);
        }
        if (key == KeyEvent.VK_UP) {
            position.setyVelocity(-1f);
        }
        if (key == KeyEvent.VK_DOWN) {
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
