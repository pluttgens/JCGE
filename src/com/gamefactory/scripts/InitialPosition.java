package com.gamefactory.scripts;

import com.gamefactory.assets.assetmanager.TypeLoader;
import com.gamefactory.assets.types.ObjectPropertiesAsset;
import com.gamefactory.components.Position;
import com.gamefactory.displayable.Scene;
import com.gamefactory.game.Game;
import com.gamefactory.services.ServiceLocator;

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
        p.initFromObjectProperties((ObjectPropertiesAsset) ServiceLocator.getAssetManager().getAsset(TypeLoader.OBJECTPROPERTIES, "hero.json"));
       // p.setDestination(new Point(300,300));

        p = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals("HEROCLIC")).findFirst().get().getComponentManager().getComponent(Position.class);
        p.setX(250);
        p.setY(250);
        p.setDefaultVelocity(5);
        p.setOffsetX(10);
        p.setOffsetY(2);
        p.setWidth(35);
        p.setHeight(50);

        Position p1 = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals("OBSTACLE")).findFirst().get().getComponentManager().getComponent(Position.class);
        p1.setX(300);
        p1.setY(300);

        Position p2 = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals("SPEEDPOTION")).findFirst().get().getComponentManager().getComponent(Position.class);
        p2.setX(500);
        p2.setY(500);
        
       /* 
        for(int i = 0; i < 10; i++){
            String id = "OBSTACLE" + i;
            System.out.println(id);
            Position pN = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals(id)).findFirst().get().getComponentManager().getComponent(Position.class);
            pN.setX(33*i);
            pN.setY(0);
        }
          */      
    }
}
