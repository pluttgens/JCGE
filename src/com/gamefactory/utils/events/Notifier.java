/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.utils.events;

import java.util.LinkedList;
import java.util.List;

/**
 * Un notifier est un objet que les classes subject doivent contenir afin de
 * pouvoir passer des events à leurs observers.
 *
 * Ce système permet de découple les senders et les receivers.
 *
 * @author Pascal Luttgens.
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class Notifier {
        
    private final Object owner;
    
    private final List<Observer> observers;
    
    public Notifier(Object owner) {
        this.observers = new LinkedList<>();
        this.owner = owner;
    }
    
    public void registerObserver(Observer o) {
        synchronized (this.observers) {
            if (!observers.contains(o)) {
                observers.add(o);
            }
        }
    }
    
    public void unregisterObserver(Observer o) {
        synchronized (this.observers) {
            observers.remove(o);
        }
    }
    
    public boolean isRegistered(Observer o) {
        synchronized (this.observers) {
            return observers.contains(o);
        }
    }
    
    public void notifyObservers(Event event) {
        synchronized (this.observers) {
            for (Observer o : this.observers) {
                o.onNotify(event);
            }
        }
    }
    
    public void notifyObservers(String event) {
        synchronized (this.observers) {
            notifyObservers(event, null);
        }
    }
    public void notifyObservers(String event, Object message) {
        synchronized (this.observers) {
            for (Observer o : this.observers) {
                o.onNotify(new Event(owner, event, message));
            }
        }
    }
}
