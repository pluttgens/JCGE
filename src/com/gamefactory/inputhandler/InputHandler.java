/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.inputhandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.gamefactory.utils.events.Notifier;
import com.gamefactory.utils.events.Subject;

/**
 *
 * @author scalpa
 */
public class InputHandler implements KeyListener, Subject {

    private final Notifier notifier;

    public InputHandler() {
        notifier = new Notifier();
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        notifier.notifyObservers((KeyEvent) ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public Notifier getNotifier() {
        return this.notifier;
    }

}
