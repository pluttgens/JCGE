package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Renderer extends Component {

    private Position position;
    private BufferedImage image;
    
    public Renderer(ComponentManager owner) {
        super(owner);
    }

    @Override
    public void init() {
        position = (Position) owner.getComponent("Position");
    }

    public void render(Graphics g) {
        script.update();
        g.drawImage(image, (int) position.getX(), (int) position.getY(), null);
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public Position getPosition() {
        return position;
    }

}
