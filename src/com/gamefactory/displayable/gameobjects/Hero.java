package com.gamefactory.displayable.gameobjects;

import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.components.scripts.PlayerInputHandler;
import com.gamefactory.displayable.Script;
import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.GameObject;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Hero extends GameObject {

    public Hero() {
        super();

    }

    @Override
    public void init() {
        componentManager.init(new Position(componentManager).registerScript(new PlayerInputHandler()), new Renderer(componentManager).registerScript(new RendererScript()));
    }

    public class RendererScript implements Script {

        private BufferedImage image;
        private Renderer renderer;

        public RendererScript() {
            try {
                this.image = ImageIO.read(new File("tileset.png"));
            } catch (IOException ex) {
                Logger.getLogger(Hero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void init(Component component) {
            renderer = (Renderer) component;
        }

        @Override
        public void update() {
            renderer.setImage(image);
        }
    }

}
