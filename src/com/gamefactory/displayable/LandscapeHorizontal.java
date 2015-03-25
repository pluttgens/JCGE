/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author ngo
 */
public class LandscapeHorizontal implements Displayable<Scene> {
    
    private Scene owner;
    private BufferedImage image;
    private int width;
    private int height;
    private int x;
    private int y;

    private Rectangle renderedArea;

    public LandscapeHorizontal() {
        
    }
    
    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), image.getWidth(), image.getHeight(), null);
 
        // Move the x position left for next time
        this.x -= 5;
 
        // Check to see if the image has gone off stage left
        if (this.x <= -1 * image.getWidth()) {
 
            // If it has, line it back up so that its left edge is
            // lined up to the right side of the other background image
            this.x = this.x + image.getWidth() * 2;
        }
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getImageWidth() {
        return image.getWidth();
    }
    
    @Override
    public void init(Scene owner) {
        this.owner = owner;
        this.width = 4000;
        this.height = 4000;
    }

    @Override
    public void update() {
    }

    public void setRenderedArea(Rectangle rectangle) {
        this.renderedArea.setBounds(rectangle);
    }

    @Override
    public void render(Graphics g) {
        
    }

    @Override
    public void load() {
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRenderedArea() {
        return renderedArea;
    }
    
}
