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
        HeroClic heroClic = new HeroClic();
        
        this.addGameObject(heroClic.getId(), heroClic);
        this.addGameObject(hero.getId(), hero);
        this.addGameObject(treasure.getId(), treasure);
        this.addGameObject(speedPotion.getId(), speedPotion);

        //Mur horizontal
        for (int i = 0 ; i < 65 ; i++){
            Obstacle oH = new Obstacle("OBSTACLEH" + i);
            this.addGameObject(oH.getId(), oH);
        }
        
        //Mur vertical
        for (int i = 0 ; i < 45; i++){
            Obstacle oV = new Obstacle("OBSTACLEV" + i);
            this.addGameObject(oV.getId(), oV);
        }
        
        //Mur obstacle
        for (int i = 0 ; i < 20; i++){
            Obstacle oO = new Obstacle("OBSTACLEO" + i);
            this.addGameObject(oO.getId(), oO);
        }
       
                

        this.addScript(new LandscapeScript(), new InitialPosition());
    }
}
