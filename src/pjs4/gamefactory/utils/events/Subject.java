/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.utils.events;

import java.util.EventObject;

/**
 *
 * @author scalpa
 */
public interface Subject {

    boolean isRegistered(Observer o);

    void notifyObservers(EventObject e);

    void registerObserver(Observer o);

    void unregisterObserver(Observer o);
    
}
