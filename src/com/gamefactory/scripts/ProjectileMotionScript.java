/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.ComponentManager;
import java.awt.Point;

/**
 *
 * @author scalpa
 */
public class ProjectileMotionScript extends UpdateScript<ComponentManager> {

    private final static String VELOCITY_KEY = ProjectileMotionScript.class.getSimpleName();
    
    private Point destination;
    private Position position;
    private int velocity;

    @Override
    public void execute() {
        if (Math.abs(position.getX() - destination.getX()) > velocity) {
            this.position.addxVelocity(VELOCITY_KEY,position.getX() > destination.getX() ? -velocity : velocity, null);
        } else if (position.getX() != destination.getX()) {
            position.addxVelocity(VELOCITY_KEY, 0, null);
            position.setX((float) destination.getX());
        }

        if (Math.abs(position.getY() - destination.getY()) > velocity) {
            this.position.addyVelocity(VELOCITY_KEY, position.getY() > destination.getY() ? -velocity : velocity, null);
        } else if (position.getY() != destination.getY()) {
            position.addyVelocity(VELOCITY_KEY, 0, null);
            position.setY((float) destination.getY());
        }
    }

    @Override
    public void load() {
        this.position = (Position) this.owner.getOwner().getComponent(Position.class);
    }

    public void setDestination(int x, int y) {
        this.destination = new Point(x, y);
    }

    public void setVelocity(int v) {
        this.velocity = v;
    }

}
