package com.gamefactory.scripts.loadingscripts;

import com.gamefactory.assets.assetmanager.TypeLoader;
import com.gamefactory.assets.types.ObjectPropertiesAsset;
import com.gamefactory.components.Position;
import com.gamefactory.displayable.Scene;
import com.gamefactory.game.Game;
import com.gamefactory.scripts.LoadingScript;
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

        Position p2 = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals("SPEEDPOTION")).findFirst().get().getComponentManager().getComponent(Position.class);
        p2.setX(500);
        p2.setY(500);
        
       
        for(int i = 0; i < 32; i++){
            String id = "OBSTACLEH" + i;
            Position pN = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals(id)).findFirst().get().getComponentManager().getComponent(Position.class);
            pN.setX(32*i);
            pN.setY(0);
        }
        
        int height = this.owner.getOwner().getLandscape().getHeight();
        for(int i = 33; i < 65; i++){
            String id = "OBSTACLEH" + i;
            Position pS = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals(id)).findFirst().get().getComponentManager().getComponent(Position.class);
            pS.setX(32*(i-33));
            pS.setY(height-32);
        }
        
        for(int i = 0; i < 22; i++){
            String id = "OBSTACLEV" + i;
            Position pW = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals(id)).findFirst().get().getComponentManager().getComponent(Position.class);
            pW.setX(0);
            pW.setY(32 + 32*i);
        }
        
        int width = this.owner.getOwner().getLandscape().getWidth();
        for(int i = 23; i < 45; i++){
            String id = "OBSTACLEV" + i;
            Position pW = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals(id)).findFirst().get().getComponentManager().getComponent(Position.class);
            pW.setX(width);
            pW.setY(32 + 32*(i-23));
        }
        
        /*
        int oX = 300;
        int oY = 150;
        for(int i = 0; i < 20; i++){
            String id = "OBSTACLEO" + i;
            Position pO = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals(id)).findFirst().get().getComponentManager().getComponent(Position.class);
            int indice = (int)(Math.random()*2);
            if(indice == 0){
                pO.setX(oX);
                pO.setY(oY + 32);
                oY = oY + 32;
            }
            
            else{
                pO.setX(oX + 32);
                pO.setY(oY);
                oX = oX + 32;
            }
            
        }
        */
        int oX = 400;
        int oY = 400;
        for(int i = 0; i < 20; i++){
            String id = "OBSTACLEO" + i;
            Position pO = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals(id)).findFirst().get().getComponentManager().getComponent(Position.class);
            if(i < 5){
                pO.setX(oX + 32);
                pO.setY(oY);
                oX = oX + 32;
            }
            else if(i < 10){
                pO.setX(oX);
                pO.setY(oY + 32);
                oY = oY + 32;
            }
            else if(i < 15){
                pO.setX(oX - 32);
                pO.setY(oY);
                oX = oX - 32;
            }
            else if(i < 17){
                pO.setX(oX);
                pO.setY(oY - 32);
                oY = oY - 32;
            }
                

        }

               
    }
}
