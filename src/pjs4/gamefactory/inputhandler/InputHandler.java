/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.inputhandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventObject;
import pjs4.gamefactory.utils.events.Notifier;
import pjs4.gamefactory.utils.events.Observer;
import pjs4.gamefactory.utils.events.Subject;

/**
 *
 * @author scalpa
 */
public class InputHandler implements KeyListener, Subject {

    public final Notifier notifier;

    public InputHandler() {
        notifier = new Notifier();
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        notifier.notifyObservers((KeyEvent) ke);
        System.out.println("l");
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent ke) {
    }

    @Override
    public boolean isRegistered(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers(EventObject e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerObserver(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unregisterObserver(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
