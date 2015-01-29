package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.game.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Renderer extends Component {

    private Position p;
    private BufferedImage image;

    public Renderer(ComponentManager owner) {
        super(owner);
        try {
            this.image = ImageIO.read(new File("tileset.png"));
        } catch (IOException ex) {
            Logger.getLogger(Renderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init() {
        p = (Position) owner.getComponent("Position");
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) p.getX(), (int) p.getY(), null);
    }

}
