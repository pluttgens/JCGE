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
        ArrayList<Obstacle> northWall = new ArrayList();
        Obstacle o=new Obstacle();
        Obstacle o1=new Obstacle();
        
        this.addGameObject(hero.getId(), hero);
        this.addGameObject(treasure.getId(), treasure);
        this.addGameObject(speedPotion.getId(), speedPotion);
        this.addGameObject(o.getId(), o);
        this.addGameObject(o1.getId(), o1);
        
        /*for (int i = 0 ; i < 10 ; i++){
            Obstacle o = new Obstacle();
            northWall.add(o);
            this.addGameObject(o.getId(), o);
        }*/

        this.addScript(new InitialPosition(), new LandscapeScript());
    }
    

    

}
