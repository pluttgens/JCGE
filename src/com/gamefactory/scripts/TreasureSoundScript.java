/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.components.Sound;
import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.listeners.ComponentListener;
import com.gamefactory.utils.timer.Timer;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author scalpa
 */
public class TreasureSoundScript extends Script {

    private Position position;
    private Sound sound;
    private Timer timer;

    @Override
    public void init(Position p) {
        super.init(p);
        this.timer = new Timer();
    }

    

    private int volume = -80;

    @Override
    public void onEvent(Position p) {
        long time = timer.getElapsedTime(TimeUnit.SECONDS);
        if (time >= 2) {
            // les bornes vont de - 80 a 6 pour ce son
            sound.getAudioEngine().playSound("test1.wav", null, volume);
            volume += 10;
            timer.resetTimer();
        }
    }

}
