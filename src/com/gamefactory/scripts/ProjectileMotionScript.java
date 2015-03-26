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
            this.position.setxVelocityDefault(position.getX() > destination.getX() ? -velocity : velocity);
        } else if (position.getX() != destination.getX()) {
            position.setxVelocityDefault(0);
            position.setX((int) destination.getX());
        }

        if (Math.abs(position.getY() - destination.getY()) > velocity) {
            this.position.setyVelocityDefault(position.getY() > destination.getY() ? -velocity : velocity);
        } else if (position.getY() != destination.getY()) {
            position.setxVelocityDefault(0);
            position.setY((int) destination.getY());
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
