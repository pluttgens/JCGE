package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.displayable.Scene;
import com.gamefactory.game.Game;
import com.gamefactory.utils.events.Event;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

public class Collider extends Component {

    public final static String COLLISION_EVENT = "COLLISION_EVENT";

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
            if (s2.equals(s1)) {
                continue;
            }
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
        // this.getComponentManager().getScriptManager().fireEvent(new Event(this, COLLISION_EVENT, c));
    }

    public Rectangle getHitbox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public static Point checkForCollisionAsMoving(Scene scene, GameObject testedGo, List<Point> points) {
        if (!testedGo.getComponentManager().checkForComponent(Collider.class)) {
            throw new IllegalArgumentException("Tested GameObject must have a Collider");
        }

        Collider testedCollider = (Collider) testedGo.getComponentManager().getComponent(Collider.class);

        Point ret = points.stream().filter(p -> scene.getGameObjects().stream().filter(go -> !go.getId().equals(testedGo.getId())).
                filter(go -> {
                    if ((p.getX() < 0 || p.getX() + testedCollider.getWidth() >= scene.getLandscape().getWidth())
                    || (p.getY() < 0 || p.getY() + testedCollider.getHeight() >= scene.getLandscape().getHeight() - Game.WINDOW_BORDER_SIZE)) {
                        return true;
                    }
                    if (go.getComponentManager().checkForComponent(Collider.class)) {
                        Collider c = (Collider) go.getComponentManager().getComponent(Collider.class);
                        if (c.getHitbox().intersects(testedCollider.getHitbox())) {
                            testedCollider.onEnterCollision(c);
                            return true;
                        }
                    }
                    return false;

                }).count() != 0).findFirst().orElse(null);

        return ret;
    }
}
