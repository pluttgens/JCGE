/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.components.Sound;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.Script;
import com.gamefactory.utils.timer.Timer;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author scalpa
 */
public class TreasureSoundScript extends Script {

    private Position selfPosition;
    private Position heroPosition;
    private Sound sound;
    private final Timer timer;

    public TreasureSoundScript() {
        this.timer = new Timer();
    }

    @Override
    public void init(ComponentManager owner) {
        super.init(owner); //To change body of generated methods, choose Tools | Templates.
        sound = (Sound) this.owner.getComponent(Sound.class);
        selfPosition = (Position) this.owner.getComponent(Position.class);
        heroPosition = (Position) this.owner.getComponentFromGO("HERO", Position.class);
        timer.start();
    }

    private int volume = -80;
    @Override
    public void update() {
        long time = timer.getElapsedTime(TimeUnit.SECONDS);
        if ( time >= 2) {
            // les bornes vont de - 80 a 6 pour ce son
            sound.getAudioEngine().playSound("test1.wav",null,volume);
            volume += 10;
            timer.resetTimer();
        }
    }

}
