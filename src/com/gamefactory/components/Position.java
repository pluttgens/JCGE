package com.gamefactory.components;

import com.gamefactory.displayable.Component;

/**
 *
 * @author scalpa
 */
public class Position extends Component {
    
    private float x;
    private float y;
    
    private float xVelocity;
    private float yVelocity;
    
    public Position() {
        this.x = 0;
        this.y = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
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

    public void update() {
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }
    
    
}
