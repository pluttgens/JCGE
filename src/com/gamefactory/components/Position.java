package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

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

    private final static int WINDOW_BORDER_SIZE = 20;

    public enum Orientation {

        UP,
        DOWN,
        LEFT,
        RIGHT

    }

    private int x;
    private int y;

    private int xVelocityDefault;
    private int yVelocityDefault;

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

    public int getxVelocityDefault() {
        return xVelocityDefault;
    }

    public void setxVelocityDefault(int xVelocityDefault) {
        this.xVelocityDefault = xVelocityDefault;
    }

    public int getyVelocityDefault() {
        return yVelocityDefault;
    }

    public void setyVelocityDefault(int yVelocityDefault) {
        this.yVelocityDefault = yVelocityDefault;
    }

    public int getxVelocity() {
        return getxVelocityDefault() != 0
                ? getxVelocityDefault() + (getxVelocityDefault() > 0
                        ? getxVelocityModifiers() : -getxVelocityModifiers()) : 0;

    }

    public int getyVelocity() {
        return getyVelocityDefault() != 0
                ? getyVelocityDefault() + (getyVelocityDefault() > 0
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

    public float distanceWith(Position position) {
        return (float) Math.hypot(this.x - position.x, this.y - position.y);
    }

    @Override
    public void update() {
        this.x += (this.x + this.getxVelocity() > 0 && this.x + this.getxVelocity() < this.owner.getScene().getLandscape().getWidth() - this.width) ? this.getxVelocity() : 0;
        this.y += (this.y + this.getyVelocity() > 0 && this.y + this.getyVelocity() < this.owner.getScene().getLandscape().getHeight() - this.height - Position.WINDOW_BORDER_SIZE) ? this.getyVelocity() : 0;
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
        hash = 79 * hash + this.xVelocityDefault;
        hash = 79 * hash + this.yVelocityDefault;
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
        if (this.xVelocityDefault != other.xVelocityDefault) {
            return false;
        }
        if (this.yVelocityDefault != other.yVelocityDefault) {
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

   

}
