/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import com.gamefactory.graphicengine.Tile;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ngo
 */
public class LandscapeHorizontal implements Displayable<Scene> {
    
    private Scene owner;
    private int width;
    private int height;

    private Rectangle renderedArea;

    private ;

    public LandscapeHorizontal() {        
        this.renderedArea = new Rectangle();
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
