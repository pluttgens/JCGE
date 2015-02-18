/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import java.awt.Graphics;

/**
 *
 * @author scalpa
 */
public class Camera implements Displayable {

    private int x;
    private int y;

    @Override
    public void init() {
        this.x = 500;
        this.y = 500;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }

}
