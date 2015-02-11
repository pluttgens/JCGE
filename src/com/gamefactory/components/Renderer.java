package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Renderer extends Component {

    private Position position;
    private BufferedImage image;

    @Override
    public void init(ComponentManager owner) {
        super.init(owner);
        position = (Position) owner.getComponent("Position");
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) position.getX(), (int) position.getY(), null);
    }

    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Position getPosition() {
        return position;
    }

}
