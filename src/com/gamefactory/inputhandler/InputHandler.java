package com.gamefactory.inputhandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.gamefactory.utils.events.Notifier;
import com.gamefactory.utils.events.Subject;

/**
 * 
 * 
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
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
