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
        if (!event.getEvent().equals(Collider.COLLISION_EVENT)) {
            return;
        }
        Collider c = (Collider) event.getMessage();
        int cH = c.getHeight();
        int cW = c.getWidth();
        int cX = c.getX();
        int cY = c.getY();
        /*
         * if(Math.abs(position.getX()-cX)-cW < 1){ position.setxVelocity(0); }
         */
        /*
         * if (Math.abs(position.getX() - cX) - cW < 1 &&
         * !(Math.abs(Math.abs(position.getY() - cY) - cH) < 2) &&
         * !(Math.abs(Math.abs(position.getY() - cY) - position.getHeight()) <
         * 3)) { if (cX > position.getX()) { position.setX(position.getX() - 1);
         * } if (cX < position.getX()) { position.setX(position.getY() + 1); } }
         * else if (Math.abs(position.getY() - cY) - cH < 1 &&
         * !(Math.abs(Math.abs(position.getX() - cX) - cW) < 10)) { if (cY >
         * position.getY()) { position.setY(position.getY() - 1); } if (cY <
         * position.getY()) { position.setY(position.getY() + 1); }
         *
         * }
         */
        /*
         * if (position.getX() + position.getWidth() > cX && position.getX() <
         * cX && (position.getY() + position.getHeight() <= cY ||
         * position.getY() >= cY + cH)) { position.setX(cX - position.getWidth()
         * - 1); } else if (position.getX() < cX + cW && position.getX() +
         * position.getWidth() > cX + cW && (position.getY() +
         * position.getHeight() <= cY || position.getY() >= cY + cH)) {
         * position.setX(cX + cW + 1); } else if ((position.getY() +
         * position.getHeight()) > cY && position.getY() < cY &&
         * (position.getX() >= cX + cW || position.getWidth() + position.getX()
         * <= cX)) { position.setY(cY - position.getHeight() - 1); } else if
         * (position.getY() < cY + cH && position.getY() + position.getHeight()
         * > cY + cH && (position.getX() + position.getWidth() <= cX ||
         * position.getX() >= cX + cW)){ position.setY(cY + cH + 1); }
         */
        /*
         * if(cX > position.getX() && Math.abs(position.getX()-cX)-cW < 1){
         * position.setX(position.getX() -1); }
         *
         *
         * if(cX < position.getX() && Math.abs(position.getX()-cX)-cW < 1){
         * position.setX(position.getX() +1); }
         *
         *
         * if(cY > position.getY() && Math.abs(position.getY()-cY)-cH < 1 &&
         * !(Math.abs(position.getX()-cX)-cW < 1)){
         * position.setY(position.getY() -1); }
         *
         * if(cY < position.getY() && Math.abs(position.getY()-cY)-cH < 1 &&
         * !(Math.abs(position.getX()-cX)-cW < 1)){
         * position.setY(position.getY() +1); }
         */
        //position.setX(position.getY() -1);
        //position.setX(position.getY() +11);
        /*
         * if(Math.abs(position.getY()-cY)-cH < 1){ position.setyVelocity(0); }
         */

        if (position.getOrientation() == Position.Orientation.DOWN) {
            position.setY(cY - position.getHeight() - 1);
        } else if (position.getOrientation() == Position.Orientation.RIGHT) {
            position.setX(cX - position.getWidth() - 1);
        } else if (position.getOrientation() == Position.Orientation.LEFT) {
            position.setX(cX + cW + 1);
        } else {
            position.setY(cY + cH + 1);
        }
    }

    @Override
    public void load() {
        this.position = (Position) owner.getOwner().getComponent(Position.class);
        this.collider = (Collider) owner.getOwner().getComponent(Collider.class);
    }

}
