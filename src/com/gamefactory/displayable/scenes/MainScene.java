/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable.scenes;

import com.gamefactory.displayable.GameObject;
import com.gamefactory.displayable.Scene;
import com.gamefactory.displayable.gameobjects.Hero;
import com.gamefactory.displayable.gameobjects.Obstacle;
import com.gamefactory.displayable.gameobjects.SpeedPotion;
import com.gamefactory.displayable.gameobjects.Treasure;
import com.gamefactory.scripts.InitialPosition;
import com.gamefactory.scripts.LandscapeScript;

/**
 *
 * @author scalpa
 */
public class MainScene extends Scene {

    @Override
    public void init() {
        GameObject hero = new Hero();
        Treasure treasure = new Treasure();
        Obstacle obstacle = new Obstacle();
        SpeedPotion speedPotion = new SpeedPotion();

        this.addGameObject(hero.getId(), hero);
        this.addGameObject(treasure.getId(), treasure);
        this.addGameObject(obstacle.getId(), obstacle);
        this.addGameObject(speedPotion.getId(), speedPotion);

        this.addScript(new InitialPosition(), new LandscapeScript());
    }

}
