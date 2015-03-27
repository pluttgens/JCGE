package com.gamefactory.displayable.gameobjects;

import com.gamefactory.components.Collider;
import com.gamefactory.components.Health;
import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.displayable.Scene;
import com.gamefactory.scripts.AnimatorFourDirections;
import com.gamefactory.scripts.BlockOnCollisionListener;
import com.gamefactory.scripts.GameObjectCameraScript;
import com.gamefactory.scripts.PlayerClikScript;
import com.gamefactory.scripts.PlayerInputHandler;

public class Hero extends GameObject {

    @Override
    public void init(Scene owner) {
        super.init(owner);

        this.componentManager.add(new Position(), new Health(), new Renderer(), new Collider());

        this.getScriptManager().add(new AnimatorFourDirections(), new PlayerInputHandler(), new PlayerClikScript(), new GameObjectCameraScript(), new BlockOnCollisionListener());
    }

}
