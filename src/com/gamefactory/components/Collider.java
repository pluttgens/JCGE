package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.utils.events.Event;
import java.awt.Rectangle;
import java.util.Iterator;

public class Collider extends Component {

    /**
     * Position du GameObject
     */
    private Position position;

    public Collider() {
    }

    @Override
    public void load() {
        this.position = (Position) this.owner.getComponent(Position.class);
    }

    @Override
    public void update() {
        Iterator<GameObject> it = this.owner.getScene().iterateOverGO();
        while (it.hasNext()) {
            GameObject go = it.next();
            String s1 = go.getId();
            String s2 = this.owner.getOwner().getId();
            if(s2.equals(s1)) break;
            if (go.getComponentManager().checkForComponent(Collider.class)) {
                Collider col2 = (Collider) go.getComponentManager().getComponent(Collider.class);
                if (this.getHitbox().intersects(col2.getHitbox())) {
                    onEnterCollision(col2);
                }
            }
        }
    }

    public int getWidth() {
        return position.getWidth();
    }

    public int getHeight() {
        return position.getHeight();
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

    public Rectangle getHitbox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
