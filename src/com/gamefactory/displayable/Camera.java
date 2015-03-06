/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import com.gamefactory.game.Game;
import com.gamefactory.scripts.CameraScript;
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
public class Camera implements Displayable {

    private Scene owner;
    private int x;
    private int y;
    private Timer timer;

    public Camera(Scene scene) {
        this.owner = scene;
        this.timer = new Timer();
    }

    @Override
    public void init() {
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

}
