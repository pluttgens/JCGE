/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable.scenes;

import com.gamefactory.displayable.GameObject;
import com.gamefactory.displayable.Scene;
import com.gamefactory.displayable.gameobjects.Hero;
import com.gamefactory.displayable.gameobjects.HeroClic;
import com.gamefactory.displayable.gameobjects.Obstacle;
import com.gamefactory.displayable.gameobjects.SpeedPotion;
import com.gamefactory.displayable.gameobjects.Treasure;
import com.gamefactory.scripts.InitialPosition;
import com.gamefactory.scripts.LandscapeScript;
import java.util.ArrayList;

/**
 *
 * @author scalpa
 */
public class MainScene extends Scene {
            
    @Override
    public void init() {
        GameObject hero = new Hero();
        Treasure treasure = new Treasure();
        SpeedPotion speedPotion = new SpeedPotion();
        Obstacle o=new Obstacle();
        Obstacle o1=new Obstacle();
        HeroClic heroClic = new HeroClic();
        
        this.addGameObject(heroClic.getId(), heroClic);
        this.addGameObject(hero.getId(), hero);
        this.addGameObject(treasure.getId(), treasure);
        this.addGameObject(speedPotion.getId(), speedPotion);
        this.addGameObject(o.getId(), o);
        this.addGameObject(o1.getId(), o1);

        this.addScript(new InitialPosition(), new LandscapeScript());
    }
}
