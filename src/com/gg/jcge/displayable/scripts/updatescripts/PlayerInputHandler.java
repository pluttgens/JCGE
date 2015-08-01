/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.displayable.scripts.updatescripts;

import com.gg.jcge.components.Position;
import com.gg.jcge.displayable.ScriptManager;
import com.gg.jcge.displayable.scripts.UpdateScript;
import com.gg.jcge.services.ServiceLocator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author scalpa
 */
public class PlayerInputHandler extends UpdateScript implements KeyListener {

    private final static int NB_KEYS = Short.MAX_VALUE;
    private final static String VELOCITY_KEY = PlayerInputHandler.class.getSimpleName();

    private Position position;
    private boolean[] keys;

    @Override
    public void init(ScriptManager script) {
        super.init(script);
        this.keys = new boolean[NB_KEYS];
        ServiceLocator.getGameWindow().getFrame().addKeyListener(this);
        ServiceLocator.getGameWindow().getCanvas().addKeyListener(this);
    }

    private boolean isDirectionalArrow(int key) {
        return (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP);
    }

    @Override
    public void load() {
        this.position = (Position) scriptManager.getComponent(Position.class);
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
    public void execute() {
        if (this.keys[KeyEvent.VK_LEFT]) {
            position.setOrientation(Position.Orientation.LEFT);
            position.setDestination(position.getDestination() == null
                    ? new Point(position.getX() - position.getDefaultVelocity(), position.getY())
                    : new Point(position.getDestination().x - position.getDefaultVelocity(), position.getDestination().y));
        } else {
            if (position.getxVelocity() < 0) {
                position.setDestination(position.getDestination() == null
                        ? new Point(position.getX(), position.getY())
                        : new Point(position.getDestination().x, position.getDestination().y));
            }
        }
        if (this.keys[KeyEvent.VK_RIGHT]) {
            position.setOrientation(Position.Orientation.RIGHT);
            position.setDestination(position.getDestination() == null
                    ? new Point(position.getX() + position.getDefaultVelocity(), position.getY())
                    : new Point(position.getDestination().x + position.getDefaultVelocity(), position.getDestination().y));
        } else {
            if (position.getxVelocity() > 0) {
                position.setDestination(position.getDestination() == null
                        ? new Point(position.getX(), position.getY())
                        : new Point(position.getDestination().x, position.getDestination().y));
            }
        }
        if (this.keys[KeyEvent.VK_UP]) {
            position.setOrientation(Position.Orientation.UP);
            position.setDestination(position.getDestination() == null
                    ? new Point(position.getX(), position.getY() - position.getDefaultVelocity())
                    : new Point(position.getDestination().x, position.getDestination().y - position.getDefaultVelocity()));
        } else {
            if (position.getyVelocity() < 0) {
                position.setDestination(position.getDestination() == null
                        ? new Point(position.getX(), position.getY())
                        : new Point(position.getDestination().x, position.getDestination().y));
            }
        }
        if (this.keys[KeyEvent.VK_DOWN]) {
            position.setOrientation(Position.Orientation.DOWN);
            position.setDestination(position.getDestination() == null
                    ? new Point(position.getX(), position.getY() + position.getDefaultVelocity())
                    : new Point(position.getDestination().x, position.getDestination().y + position.getDefaultVelocity()));

        } else {
            if (position.getyVelocity() > 0) {
                position.setDestination(position.getDestination() == null
                        ? new Point(position.getX(), position.getY())
                        : new Point(position.getDestination().x, position.getDestination().y));
            }
        }
    }
}
