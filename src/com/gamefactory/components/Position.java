package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static javafx.scene.paint.Color.color;

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

    private boolean hasCollisionHappened;

    private int defaultVelocity;
    
    private int x;
    private int y;

    private int xMainVelocity;
    private int yMainVelocity;

    private HashMap<String, Integer> xVelocityModifiers;
    private HashMap<String, Integer> yVelocityModifiers;

    private ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);

    private Orientation orientation;

    private int height;
    private int width;

    public Position() {
        this.x = 0;
        this.y = 520;
        this.xVelocityModifiers = new HashMap<>();
        this.yVelocityModifiers = new HashMap<>();
        this.height = 0;
        this.width = 0;
        this.orientation = Orientation.DOWN;
        this.hasCollisionHappened = false;
    }

    /**
     * Recupere la valeur en abscisse
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Initialise les valeurs en abscisse
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Recupere la valeur en ordonnee
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Initialise les valeurs en ordonnee
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    public int getDefaultVelocity() {
        return defaultVelocity;
    }
    
    public void setDefaultVelocity(int defaultVelocity) {
        this.defaultVelocity = defaultVelocity;
    }
    
    public int getxMainVelocity() {
        return xMainVelocity;
    }

    public void setxMainVelocity(int xVelocityDefault) {
        this.xMainVelocity = xVelocityDefault;
    }

    public int getyMainVelocity() {
        return yMainVelocity;
    }

    public void setyMainVelocity(int yVelocityDefault) {
        this.yMainVelocity = yVelocityDefault;
    }

    public int getxVelocity() {
        return getxMainVelocity() != 0
                ? getxMainVelocity() + (getxMainVelocity() > 0
                        ? getxVelocityModifiers() : -getxVelocityModifiers()) : 0;

    }

    public int getyVelocity() {
        return getyMainVelocity() != 0
                ? getyMainVelocity() + (getyMainVelocity() > 0
                        ? getyVelocityModifiers() : -getyVelocityModifiers()) : 0;

    }

    /**
     * Recupere la vitesse de deplacement du GameObject de haut en bas (abscisse
     * x)
     *
     * @return
     */
    public Integer getxVelocityModifiers() {
        return xVelocityModifiers.values().stream().reduce(Integer::sum).orElseGet(() -> 0);
    }

    /**
     * Initialise la vitesse de deplacement du GameObject de haut en bas
     * (abscisse x)
     *
     * @param xVelocity
     */
    public void setxVelocityModifiers(HashMap<String, Integer> xVelocityModifiers) {
        this.xVelocityModifiers = xVelocityModifiers;
    }

    public void setxVelocityModifiers(String key, Integer xVelocityModifiers) {
        this.xVelocityModifiers.clear();
        this.xVelocityModifiers.put(key, xVelocityModifiers);
    }

    public void addxVelocityModifiers(String key, Integer velocity, Integer time) {
        xVelocityModifiers.put(key, velocity);
        if (time != null) {
            ses.schedule(() -> {
                xVelocityModifiers.remove(key);
            }, time, TimeUnit.SECONDS);
        }
    }

    /**
     * Recupere la vitesse de deplacement du GameObject de gauche � droite
     * (ordonnee y)
     *
     * @return
     */
    public Integer getyVelocityModifiers() {
        return yVelocityModifiers.values().stream().reduce(Integer::sum).orElseGet(() -> 0);
    }

    /**
     * Initialise la vitesse de deplacement du GameObject de gauche a droite
     * (ordonnee y)
     *
     * @param yVelocity
     */
    public void setyVelocityModifiers(HashMap<String, Integer> yVelocityModifiers) {
        this.yVelocityModifiers = yVelocityModifiers;
    }

    public void setyVelocityModifiers(String key, Integer yVelocityModifiers) {
        this.yVelocityModifiers.clear();
        this.yVelocityModifiers.put(key, yVelocityModifiers);
    }

    public void addyVelocityModifiers(String key, Integer velocity, Integer time) {
        yVelocityModifiers.put(key, velocity);
        if (time != null) {
            ses.schedule(() -> {
                yVelocityModifiers.remove(key);
            }, time, TimeUnit.SECONDS);
        }
    }

    /**
     * Recupere la hauteur du GameObject
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Initialise la taille du GameObject
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Recupere la largeur du GameObject
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Initialise la largeur du GameObject
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Recupere l'orientation du GameObject
     *
     * @return
     */
    public Orientation getOrientation() {
        return this.orientation;
    }

    /**
     * Initialise l'orientation du GameObject
     *
     * @param orientation
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setHasCollisionHappened(boolean hasCollisionHappened) {
        this.hasCollisionHappened = hasCollisionHappened;
    }

    public float distanceWith(Position position) {
        return (float) Math.hypot(this.x - position.x, this.y - position.y);
    }

    @Override
    public void update() {
        
        if (!this.hasCollisionHappened) {
            List<Point> line = line(x, y, this.x + this.getxVelocity(), this.y + this.getyVelocity());
            if (this.owner.checkForComponent(Collider.class)) {
                Point p = Collider.checkForCollisionAsMoving(this.getComponentManager().getScene(), this.getComponentManager().getOwner(), line);
                if (p != null) {
                    int i = line.indexOf(p);
                    if (i > 0) {   
                        p = line.get(i - 1);
                        //System.out.println("x2:" + p.x + " y2:" + p.y);
                        this.x = (int) p.getX();
                        this.y = (int) p.getY();
                    }
                } else {
                    this.x += this.getxVelocity();
                    this.y += this.getyVelocity();
                }
            }
        }
        this.hasCollisionHappened = false;
    }

    @Override
    protected int getUpdatePriority() {
        return Integer.MAX_VALUE;
    }

    public Position deepClone() {
        Position ret = new Position();
        ret.setX(this.x);
        ret.setY(this.y);
        ret.setOrientation(this.getOrientation());
        ret.setxVelocityModifiers(this.xVelocityModifiers);
        ret.setyVelocityModifiers(this.yVelocityModifiers);
        ret.setHeight(this.height);
        ret.setWidth(this.width);
        return ret;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
        hash = 79 * hash + this.xMainVelocity;
        hash = 79 * hash + this.yMainVelocity;
        hash = 79 * hash + Objects.hashCode(this.xVelocityModifiers);
        hash = 79 * hash + Objects.hashCode(this.yVelocityModifiers);
        hash = 79 * hash + Objects.hashCode(this.ses);
        hash = 79 * hash + Objects.hashCode(this.orientation);
        hash = 79 * hash + this.height;
        hash = 79 * hash + this.width;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.xMainVelocity != other.xMainVelocity) {
            return false;
        }
        if (this.yMainVelocity != other.yMainVelocity) {
            return false;
        }
        if (!Objects.equals(this.xVelocityModifiers, other.xVelocityModifiers)) {
            return false;
        }
        if (!Objects.equals(this.yVelocityModifiers, other.yVelocityModifiers)) {
            return false;
        }
        if (!Objects.equals(this.ses, other.ses)) {
            return false;
        }
        if (this.orientation != other.orientation) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        return true;
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
