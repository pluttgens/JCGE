package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.utils.events.Event;
import com.gamefactory.utils.events.Notifier;
import java.awt.event.AWTEventListener;

/**
 * Component permettant de gérer la position d'une unité et ses déplacements
 * basiques.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class Position extends Component {

    public enum Orientation {

        UP,
        DOWN,
        LEFT,
        RIGHT

    }

    private float x;
    private float y;

    private float xVelocity;
    private float yVelocity;

    private Orientation orientation;

    public Position() {
        this.x = 0;
        this.y = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.orientation = Orientation.DOWN;
    }

    @Override
    public void init(ComponentManager owner) {
        super.init(owner);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public float distanceWith(Position position) {
        return (float) Math.hypot(this.x - position.x, this.y - position.y);
    }

    @Override
    public void update() {
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }

    @Override
    protected int getUpdatePriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onNotify(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
