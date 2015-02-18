/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable.gameobjects;

import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.components.Sound;
import com.gamefactory.displayable.GameObject;
import com.gamefactory.scripts.PlayerFindTreasureScript;
import com.gamefactory.scripts.TreasurePositionScript;
import com.gamefactory.scripts.TreasureSoundScript;

/**
 *
 * @author scalpa
 */
public class Treasure extends GameObject {

    public Treasure() {
        super();
    }

    @Override
    public void init() {
        this.componentManager.init(new Position(), new Renderer(), new Sound(), new TreasureSoundScript(),new TreasurePositionScript(), new PlayerFindTreasureScript());
    }
}
