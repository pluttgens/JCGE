/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.Scene;
import com.gamefactory.game.Game;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 *
 * @author Adrien
 */
public class InitialPosition extends LoadingScript<Scene> {


    @Override
    public void load() {
        
    }
    
    

    @Override
    public void executeOnce() {
        this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals("TREASURE")).forEach(go -> {
            Position p = (Position) go.getComponentManager().getComponent(Position.class);
            p.setX((int) (Math.random() * Game.WIDTH));
            p.setY((int) (Math.random() * Game.HEIGHT));
        });

        Position p = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals("HERO")).findFirst().get().getComponentManager().getComponent(Position.class);
        p.setX(200);
        p.setY(200);
        
        Position p1 = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals("OBSTACLE")).findFirst().get().getComponentManager().getComponent(Position.class);
        p1.setX(300);
        p1.setY(300);
    }

}
