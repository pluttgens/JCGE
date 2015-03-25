package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import java.awt.Graphics;
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
        position = (Position) owner.getComponent(Position.class);
        try {
            collider = (Collider) owner.getComponent(Collider.class);
        } catch (java.lang.IllegalStateException e) {
            //rien
        }
    }

    public void render(Graphics g) {
        if (isActived) {
            g.drawImage(image, (int) position.getX() - this.owner.getScene().getCamera().getX(), (int) position.getY() - this.owner.getScene().getCamera().getY(), null);
        }
    }

    /**
     *
     * @return l'image actuellement affichée par le component
     */
    public BufferedImage getImage() {
        return image;
    }
    
    public void enable() {
        this.isActived = true;
    }
    
    public void disable() {
        this.isActived = false;
    }

    /**
     * Permet de changer l'image actuellement affichée par le component
     *
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        this.position.setHeight(this.image.getHeight());
        this.position.setWidth(this.image.getWidth());
    }

    /**
     *
     * @return la position de l'image
     */
    public Position getPosition() {
        return position;
    }

}
