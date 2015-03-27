/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.ScriptManager;
import com.gamefactory.game.Game;
import com.gamefactory.services.ServiceLocator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ngo
 */
public class PlayerInputHandlerProjectile extends UpdateScript<ComponentManager> implements KeyListener {

    private final static int NB_KEYS = Short.MAX_VALUE;
    private final static String VELOCITY_KEY = PlayerInputHandler.class.getSimpleName();
    
    private Position position;
    private boolean[] keys;
    private int velocity;

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }


    @Override
    public void init(ScriptManager script) {
        super.init(script);
        this.keys = new boolean[NB_KEYS];
        this.setVelocity(1);
        ServiceLocator.getGameWindow().getFrame().addKeyListener(this);
        ServiceLocator.getGameWindow().getCanvas().addKeyListener(this);
    }

    private boolean isKeySpace(int key) {
        return key == KeyEvent.VK_SPACE;
    }

    @Override
    public void load() {
        this.position = (Position) owner.getOwner().getComponent(Position.class);
        this.position.setX(Game.WIDTH / 2 - 20);
        this.position.setY(Game.HEIGHT / 2 - 20);
    }
    
    

    private void resetKeySpace() {
        this.keys[KeyEvent.VK_SPACE] = false;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (isKeySpace(key)) {
            resetKeySpace();
            this.keys[key] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        this.keys[ke.getKeyCode()] = false;
    }

    @Override
    public void execute() {
        if(this.keys[KeyEvent.VK_SPACE]) {
            
        }
        else {
            if (position.getxVelocity() < 0) {
                position.setxMainVelocity(0);
            }
        }
    }
    
}