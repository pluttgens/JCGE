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

    private float x;
    private float y;

    private HashMap<String, Integer> xVelocity;
    private HashMap<String, Integer> yVelocity;

    private ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);

    private Orientation orientation;

    private int height;
    private int width;

    public Position() {
        this.x = 0;
        this.y = 520;
        this.xVelocity = new HashMap<>();
        this.yVelocity = new HashMap<>();
        this.height = 0;
        this.width = 0;
        this.orientation = Orientation.DOWN;
    }

    /**
     * Recupere la valeur en abscisse
     *
     * @return
     */
    public float getX() {
        return x;
    }

    /**
     * Initialise les valeurs en abscisse
     *
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Recupere la valeur en ordonnee
     *
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     * Initialise les valeurs en ordonnee
     *
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Recupere la vitesse de deplacement du GameObject de haut en bas (abscisse
     * x)
     *
     * @return
     */
    public Integer getxVelocity() {
        return xVelocity.values().stream().reduce(Integer::sum).orElseGet(() -> 0);
    }

    /**
     * Initialise la vitesse de deplacement du GameObject de haut en bas
     * (abscisse x)
     *
     * @param xVelocity
     */
    public void setxVelocity(HashMap<String, Integer> xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setxVelocity(String key, Integer xVelocity) {
        this.xVelocity.clear();
        this.xVelocity.put(key, xVelocity);
    }

    public void addxVelocity(String key, Integer velocity, Integer time) {
        xVelocity.put(key, velocity);
        if (time != null) {
            ses.schedule(() -> {
                xVelocity.remove(key);
            }, time, TimeUnit.SECONDS);
        }
    }

    /**
     * Recupere la vitesse de deplacement du GameObject de gauche � droite
     * (ordonnee y)
     *
     * @return
     */
    public Integer getyVelocity() {
        return yVelocity.values().stream().reduce(Integer::sum).orElseGet(() -> 0);
    }

    /**
     * Initialise la vitesse de deplacement du GameObject de gauche a droite
     * (ordonnee y)
     *
     * @param yVelocity
     */
    public void setyVelocity(HashMap<String, Integer> yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setyVelocity(String key, Integer yVelocity) {
        this.yVelocity.clear();
        this.yVelocity.put(key, yVelocity);
    }

    public void addyVelocity(String key, Integer velocity, Integer time) {
        yVelocity.put(key, velocity);
        if (time != null) {
            ses.schedule(() -> {
                yVelocity.remove(key);
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
        ret.setxVelocity(this.xVelocity);
        ret.setyVelocity(this.yVelocity);
        ret.setHeight(this.height);
        ret.setWidth(this.width);
        return ret;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Float.floatToIntBits(this.x);
        hash = 43 * hash + Float.floatToIntBits(this.y);
        hash = 43 * hash + (this.getxVelocity());
        hash = 43 * hash + (this.getyVelocity());
        hash = 43 * hash + Objects.hashCode(this.orientation);
        hash = 43 * hash + this.height;
        hash = 43 * hash + this.width;
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
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        if ((this.getxVelocity()) != Float.floatToIntBits(other.getxVelocity())) {
            return false;
        }
        if ((this.getyVelocity()) != Float.floatToIntBits(other.getyVelocity())) {
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
