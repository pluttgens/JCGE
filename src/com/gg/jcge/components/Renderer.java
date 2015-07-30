package com.gg.jcge.components;

import com.gg.jcge.displayable.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer extends Component {

    /**
     * Position de l'image à afficher
     */
    private Position position;

    /**
     * Collider à afficher (Rectangle)
     */
    private Collider collider;

    /**
     * Image affichée par le component
     */
    private BufferedImage image;

    private boolean isActived = true;

    @Override
    public void load() {
        position = (Position) componentManager.getComponent(Position.class);
        try {
            collider = (Collider) componentManager.getComponent(Collider.class);
        } catch (java.lang.IllegalStateException e) {
            //rien
        }
    }

    public void render(Graphics g) {
        if (isActived) {
            g.drawImage(image, position.getX() - this.componentManager.getScene().getCamera().getX(), position.getY() - this.componentManager.getScene().getCamera().getY(), null);
        }
    }

    /**
     *
     * @return l'image actuellement affichée par le component
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Permet de changer l'image actuellement affichée par le component
     *
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        if (position.getHeight() == 0) {
            this.position.setHeight(this.image.getHeight());
        }
        if (position.getWidth() == 0) {
            this.position.setWidth(this.image.getWidth());
        }
    }

    public void enable() {
        this.isActived = true;
    }

    public void disable() {
        this.isActived = false;
    }

    /**
     *
     * @return la position de l'image
     */
    public Position getPosition() {
        return position;
    }

}
