package pjs4.gamefactory.components;

import pjs4.gamefactory.displayable.Component;

/**
 *
 * @author scalpa
 */
public class Position implements Component {
    
    private float x;
    private float y;
    
    private float xVelocity;
    private float yVelocity;
    
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
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
