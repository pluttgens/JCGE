/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gamefactory.scripts;

import com.gamefactory.components.Collider;
import com.gamefactory.components.Position;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.GameObject;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import com.gamefactory.displayable.Script;

/**
 *
 * @author Adrien
 */
public class PlayerCollision extends Script{
     @Override
    public void init(ComponentManager owner) {
        super.init(owner);
        this.position = (Position) this.owner.getComponent(Position.class);
    }

    @Override
    public void update() {
        //ArrayList<GameObject> gameObjects = owner.getScene().getGameObjects();
        Iterator<GameObject> it = this.owner.getScene().iterateOverGO();
        while (it.hasNext()) {
            ComponentManager cm = it.next().getComponentManager();
            if (cm.checkForComponent("Collider") /*&& (this.owner.getGameObject().getId().equals(cm.getGameObject().getId()))*/) {
                Collider col2 = (Collider) it.next().getComponentManager().getComponent("Collider");
                if (this.getX() < col2.getX() + col2.getWidth()
                        && this.getX() + this.getWidth() > col2.getX()
                        && this.getY() < col2.getY() + col2.getHeight()
                        && this.getHeight() + this.getY() > col2.getY()) {
                    this.owner.onEnterCollision(col2.getOwner().getGameObject().getId());
                    col2.getOwner().onEnterCollision(this.owner.getGameObject().getId());
                }
            }
        }
    }

    /**
     * Taille de rectangle
     */
    private int width;
    private int height;

    /**
     * Position du GameObject
     */
    private Position position;

    public PlayerCollision() {
        width = 20;
        height = 20;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int) position.getX(), (int) position.getY(), width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return (int) position.getX();
    }

    public int getY() {
        return (int) position.getY();
    }
    
    @Override
    public void onEnterCollision(String id) {
    	System.out.println("Bonjour je colisionne avec " + id);
        position.setxVelocity(0);
        position.setyVelocity(0);
    }

   
}
