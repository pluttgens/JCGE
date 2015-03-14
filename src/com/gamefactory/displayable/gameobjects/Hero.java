package com.gamefactory.displayable.gameobjects;

import com.gamefactory.components.Collider;
import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.scripts.AnimatorFourDirections;
import com.gamefactory.scripts.BlockOnCollisionScript;
import com.gamefactory.scripts.PlayerInputHandler;

public class Hero extends GameObject {

    public Hero() {
        super();
    }

    @Override
    public void onLoading() {
        componentManager.init(new Position(), new Renderer(), new AnimatorFourDirections(), new PlayerInputHandler(), new Collider(), new BlockOnCollisionScript());
    }

}
