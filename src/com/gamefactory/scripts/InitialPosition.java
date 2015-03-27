/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.displayable.Scene;
import com.gamefactory.game.Game;
import java.util.List;
import java.util.stream.Collectors;

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

        Position p2 = (Position) this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals("SPEEDPOTION")).findFirst().get().getComponentManager().getComponent(Position.class);
        p2.setX(500);
        p2.setY(500);

        List<Position> positionNorthWall = this.owner.getOwner().getGameObjects().stream().filter(go -> go.getId().equals("OBSTACLE")).map((GameObject t) -> (Position) t.getComponentManager().getComponent(Position.class)).collect(Collectors.toList());
        for (int i = 0; i < positionNorthWall.size(); i++) {
            positionNorthWall.get(i).setX(0);
            positionNorthWall.get(i).setY(150);
        }
    }

}
