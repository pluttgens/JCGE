/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable.gameobjects;

import com.gamefactory.components.Collider;
import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.components.Sound;
import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.scripts.PlayerFindTreasureScript;
import com.gamefactory.scripts.InitialPosition;
import com.gamefactory.scripts.TreasureSoundScript;
import com.gamefactory.utils.builders.ArrayBuilder;
/**
 *
 * @author scalpa
 */
public class Treasure extends GameObject {

    public Treasure() {
        super();
    }

    @Override
    public void load() {
        this.componentManager.init(Component.build(Position.class, new TreasureSoundScript(), null), Component.build(Renderer.class, scripts, listeners),/* new TreasureSoundScript(),*/Component.build(Sound.class, ArrayBuilder.asArray(new TreasureSoundScript()) ,null));
    }

}
