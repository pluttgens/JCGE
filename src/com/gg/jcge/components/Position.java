package com.gg.jcge.components;

import com.gg.jcge.assets.types.ObjectPropertiesAsset;
import com.gg.jcge.displayable.Component;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int offsetX;
    private int offsetY;
    private int height;
    private int width;
    private Point destination;
    private Point nextPosition;
    private Point collisionPoint;
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

    public void setxMainVelocity(int xMainVelocity) {
        this.xMainVelocity = xMainVelocity;
    }

    public int getyMainVelocity() {
        return yMainVelocity;
    }

    public void setyMainVelocity(int yMainVelocity) {
        this.yMainVelocity = yMainVelocity;
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

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
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

        if (collisionPoint != null) {
            this.x = (int) this.collisionPoint.getX();
            this.y = (int) this.collisionPoint.getY();
            this.collisionPoint = null;
        } else if (this.nextPosition != null) {
            this.x = (int) this.nextPosition.getX();
            this.y = (int) this.nextPosition.getY();

        }
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
        if (this.destination.x > this.x) {
            this.xMainVelocity = this.getDefaultVelocity();
        } else if (this.destination.x < this.x) {
            this.xMainVelocity = -this.getDefaultVelocity();
        } else {
            this.xMainVelocity = 0;
        }

        if (this.destination.y > this.y) {
            this.yMainVelocity = this.getDefaultVelocity();
        } else if (this.destination.y < this.y) {
            this.yMainVelocity = -this.getDefaultVelocity();
        } else {
            this.yMainVelocity = 0;
        }
    }

    public Point getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Point nextPosition) {
        this.nextPosition = nextPosition;
    }

    public Point getCollisionPoint() {
        return collisionPoint;
    }

    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
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

    public void initFromObjectProperties(ObjectPropertiesAsset propertiesAsset) {
        Map<String, String> properties = propertiesAsset.retrieve(this.getClass().getSimpleName().toLowerCase());
        System.out.println(Arrays.toString(this.getClass().getFields()));
        for (Map.Entry<String, String> entrySet : properties.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();

            for (Field f : this.getClass().getDeclaredFields()) {
                if (f.getName().equals(key)) {
                    try {
                        f.set(this, Integer.parseInt(value));
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(Position.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public enum Orientation {

        UP,
        DOWN,
        LEFT,
        RIGHT

    }

}
