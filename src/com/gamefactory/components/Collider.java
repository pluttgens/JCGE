package com.gamefactory.components;

import java.awt.Color;
import java.awt.Graphics;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.utils.events.Event;
import java.awt.event.ComponentListener;
import java.util.Iterator;
import java.util.List;

public class Collider extends Component {

    /**
     * Taille de rectangle
     */
    private int width;
    private int height;

    /**
     * Position du GameObject
     */
    private Position position;

    public Collider() {
    }

    @Override
    public void load() {
        this.position = (Position) this.owner.getComponent(Position.class);
        this.width = position.getWidth();
        this.height = position.getHeight();
    }

    @Override
    public void update() {
        Iterator<GameObject> it = this.owner.getScene().iterateOverGO();
        while (it.hasNext()) {
            GameObject go = it.next();
            if (go.getComponentManager().checkForComponent("Collider") ) {
                Collider col2 = (Collider) it.next().getComponentManager().getComponent("Collider");
                if (this.getX() < col2.getX() + col2.getWidth()
                        && this.getX() + this.getWidth() > col2.getX()
                        && this.getY() < col2.getY() + col2.getHeight()
                        && this.getHeight() + this.getY() > col2.getY()) {
                    onEnterCollision(col2);
                }
            }
        }
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

    public void onEnterCollision(Collider c) {
        this.getComponentManager().getScriptManager().fireEvent(new Event(this, null, c));
    }
}
