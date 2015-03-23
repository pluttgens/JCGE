 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.components.Sound;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.ScriptManager;
import com.gamefactory.utils.timer.Timer;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author scalpa
 */
public class TreasureSoundScript extends UpdateScript<ComponentManager> {

    private Position treasurePosition;
    private Position heroPosition;
    private Sound sound;
    private Timer timer;

    @Override
    public void init(ScriptManager script) {
        super.init(script);
        this.timer = new Timer();
    }

    @Override
    public void load() {
        this.sound = (Sound) this.owner.getOwner().getComponent(Sound.class);
        this.treasurePosition = (Position) this.owner.getOwner().getComponent(Position.class);
        this.heroPosition = (Position) this.owner.getOwner().getComponentFromGO("HERO", Position.class);
        timer.start();
    }

    

    private int volume = -80;

    @Override
    public void execute() {
        long time = timer.getElapsedTime(TimeUnit.SECONDS);
        if (time >= 2) {
            // les bornes vont de - 80 a 6 pour ce son
            sound.getAudioEngine().playSound("test1.wav", null, volume);
            volume += 10;
            timer.resetTimer();
        }
    }

}
