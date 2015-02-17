package com.gamefactory.displayable.gameobjects;

import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.scripts.PlayerInputHandler;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.scripts.AnimatorFourDirections;

public class Hero extends GameObject {

    public Hero() {
        super();
    }

    @Override
    public void init() {
        componentManager.init(new Position(), new Renderer(), new AnimatorFourDirections(), new PlayerInputHandler());
    }

}
