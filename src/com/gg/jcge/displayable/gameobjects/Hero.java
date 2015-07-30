package com.gg.jcge.displayable.gameobjects;

import com.gg.jcge.components.Collider;
import com.gg.jcge.components.Health;
import com.gg.jcge.components.Position;
import com.gg.jcge.components.Renderer;
import com.gg.jcge.displayable.GameObject;
import com.gg.jcge.displayable.Scene;

public class Hero extends GameObject {

    @Override
    public void init(Scene owner) {
        super.init(owner);

        this.componentManager.add(new Position(), new Health(), new Renderer(), new Collider());

        //  this.getScriptManager().add(new AnimatorFourDirections(), new PlayerInputHandler());
    }

}
