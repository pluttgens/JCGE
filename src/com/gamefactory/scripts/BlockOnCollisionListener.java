/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Collider;
import com.gamefactory.components.Position;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.ScriptManager;
import com.gamefactory.utils.events.Event;

public class BlockOnCollisionListener extends ListenerScript<ComponentManager> {
    
    private Position position;
    private Collider collider;
    
    @Override
    public void init(ScriptManager sm) {
        super.init(sm);
    }

    @Override
    public void onEvent(Event event) {
        Collider c = (Collider)event.getMessage();
        int cH = c.getHeight();
        int cW = c.getWidth();
        int cX = c.getX();
        int cY = c.getY();
        /*if(Math.abs(position.getX()-cX)-cW < 1){
            position.setxVelocity(0);
        }*/

        if(Math.abs(position.getX()-cX)-cW < 1 && !(Math.abs(Math.abs(position.getY()-cY)- cH) < 2) && !(Math.abs(Math.abs(position.getY()-cY)- position.getHeight()) < 3)){
            if(cX > position.getX()) position.setX(position.getX() -1);
            if(cX < position.getX()) position.setX(position.getX() +1);
        }
        
        else if(Math.abs(position.getY()-cY)-cH < 1 && !(Math.abs(Math.abs(position.getX()-cX)- cW) < 10)){
            if(cY > position.getY()) position.setY(position.getY() -1);
            if(cY < position.getY()) position.setY(position.getY() +1);
        }
        
        /*
        if(cX > position.getX() && Math.abs(position.getX()-cX)-cW < 1){
           position.setX(position.getX() -1);
        }
        
        
        if(cX < position.getX() && Math.abs(position.getX()-cX)-cW < 1){
            position.setX(position.getX() +1);
        }
        
        
        if(cY > position.getY() && Math.abs(position.getY()-cY)-cH < 1 && !(Math.abs(position.getX()-cX)-cW < 1)){
           position.setY(position.getY() -1);
        }
        
        if(cY < position.getY() && Math.abs(position.getY()-cY)-cH < 1 && !(Math.abs(position.getX()-cX)-cW < 1)){
           position.setY(position.getY() +1);
        }
        */
        
        //position.setX(position.getY() -1);
        
        //position.setX(position.getY() +11);
        
        /*
        if(Math.abs(position.getY()-cY)-cH < 1){
            position.setyVelocity(0);
        }
                */
    }   

    @Override
    public void load() {
        this.position = (Position)owner.getOwner().getComponent(Position.class);
        this.collider = (Collider)owner.getOwner().getComponent(Collider.class);
    }
    
}
