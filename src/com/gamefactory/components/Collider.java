package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.game.Game;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
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
        if (position.getDestination() != null) {
            if (Math.abs(position.getX() - position.getDestination().x) > Math.abs(position.getxVelocity())) {
                position.setNextPosition(new Point(position.getX() + position.getxVelocity(), position.getNextPosition() == null ? position.getY() : position.getNextPosition().y));
            }
            if (Math.abs(position.getY() - position.getDestination().y) > Math.abs(position.getyVelocity())) {
                position.setNextPosition(new Point(position.getNextPosition() == null ? position.getX() : position.getNextPosition().x, position.getY() + position.getyVelocity()));
            }
        }

        if (position.getNextPosition() != null) {
            List<Point> points = line(position.getX(), position.getY(), (int) position.getNextPosition().getX(), (int) position.getNextPosition().getY());
            Point collisionPoint = points.stream().filter(p -> owner.getScene().getGameObjects().stream().filter(go -> !go.getId().equals(owner.getOwner().getId())).
                    filter(go -> {
                        if ((p.getX() < 0 || p.getX() + getWidth() >= owner.getScene().getLandscape().getWidth())
                        || (p.getY() < 0 || p.getY() + getHeight() >= owner.getScene().getLandscape().getHeight() - Game.WINDOW_BORDER_SIZE)) {
                            return true;
                        }
                        if (go.getComponentManager().checkForComponent(Collider.class)) {
                            Collider c = (Collider) go.getComponentManager().getComponent(Collider.class);
                            Rectangle r = new Rectangle(p.x, p.y, position.getWidth(), position.getHeight());
                            if (c.getHitbox().intersects(r)) {
                                onEnterCollision(c);
                                return true;
                            }
                        }
                        return false;

                    }).count() != 0).findFirst().orElse(null);
            if (collisionPoint != null) {
                int i = points.indexOf(collisionPoint);
                if (i > 0) {
                    collisionPoint = points.get(i - 1);
                    //System.out.println("x2:" + p.x + " y2:" + p.y);
                } else {
                    collisionPoint.setLocation(new Point(position.getX(), position.getY()));
                }

                position.setCollisionPoint(new Point(collisionPoint.x, collisionPoint.y));
            }
        } else {

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

    public List<Point> line(int x, int y, int x2, int y2) {
        ArrayList<Point> line = new ArrayList<>();
        int w = x2 - x;
        int h = y2 - y;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if (w < 0) {
            dx1 = -1;
        } else if (w > 0) {
            dx1 = 1;
        }
        if (h < 0) {
            dy1 = -1;
        } else if (h > 0) {
            dy1 = 1;
        }
        if (w < 0) {
            dx2 = -1;
        } else if (w > 0) {
            dx2 = 1;
        }
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        if (!(longest > shortest)) {
            longest = Math.abs(h);
            shortest = Math.abs(w);
            if (h < 0) {
                dy2 = -1;
            } else if (h > 0) {
                dy2 = 1;
            }
            dx2 = 0;
        }
        int numerator = longest >> 1;
        for (int i = 0; i <= longest; i++) {
            line.add(new Point(x, y));
            numerator += shortest;
            if (!(numerator < longest)) {
                numerator -= longest;
                x += dx1;
                y += dy1;
            } else {
                x += dx2;
                y += dy2;
            }
        }
        return line;
    }
}
