/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import com.gamefactory.game.Game;
import com.gamefactory.scripts.GameObjectCameraScript;
import com.gamefactory.utils.timer.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.concurrent.TimeUnit;

/**
 * La camera représente ce que le joueur peut voir du paysage à travers la
 * fenetre de jeu.
 *
 * @author Pascal Luttgens
 *
 * @version 2.0
 *
 * @since 2.0
 */
public class Camera implements Displayable<Scene> {

    private Scene owner;
    private int x;
    private int y;
    private Timer timer;

    public Camera() {
    }

    
    
    @Override
    public void init(Scene owner) {
        this.owner = owner;
        this.timer = new Timer();
    }

    
    
    @Override
    public void load() {
        this.x = 0;
        this.y = 0;
        this.timer.start();
    }

    @Override
    public void update() {
        if (timer.getElapsedTime(TimeUnit.SECONDS) >= 2) {
            this.x -= 50;
            this.owner.getLandscape().setRenderedArea(new Rectangle(new Point(x, y), new Dimension(Game.WIDTH, Game.HEIGHT)));
            timer.resetTimer();
        }
    }

    @Override
    public void render(Graphics g) {
        owner.getLandscape().render(g);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Scene getOwner() {
        return owner;
    }

    public void setOwner(Scene owner) {
        this.owner = owner;
    }

    
    
}
