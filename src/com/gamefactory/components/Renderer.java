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
     * Image affichée par le component
     */
    private BufferedImage image;

    @Override
    public void init(ComponentManager owner) {
        super.init(owner);
        position = (Position) owner.getComponent(Position.class);
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) position.getX(), (int) position.getY(), null);
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
