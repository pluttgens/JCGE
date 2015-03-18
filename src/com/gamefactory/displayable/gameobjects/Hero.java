package com.gamefactory.displayable.gameobjects;

import com.gamefactory.components.Collider;
import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.displayable.Script;
import com.gamefactory.scripts.AnimatorFourDirections;
import com.gamefactory.listeners.BlockOnCollisionListener;
import com.gamefactory.scripts.PlayerInputHandler;
import com.gamefactory.utils.builders.ArrayBuilder;

public class Hero extends GameObject {


    @Override
    public void load() {
        componentManager.init(Component.build(Position.class, (Script<Component>[]) ArrayBuilder.asArray(new AnimatorFourDirections(), new PlayerInputHandler()), null),
                Component.build(Renderer.class, null, ArrayBuilder.asArray(new BlockOnCollisionListener())), new Renderer());
    }

}
