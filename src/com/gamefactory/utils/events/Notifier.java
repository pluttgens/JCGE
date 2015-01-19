/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.utils.events;

import java.util.EventObject;
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
    
    private final Object lock;
    
    private List<Observer> observers;
    
    public Notifier() {
        this.observers = new LinkedList<>();
        this.lock = new Object();
    }
    
    public void registerObserver(Observer o) {
        synchronized (lock) {
            if (!observers.contains(o)) {
                observers.add(o);
            }
        }
    }
    
    public void unregisterObserver(Observer o) {
        synchronized (lock) {
            observers.remove(o);
        }
    }
    
    public boolean isRegistered(Observer o) {
        synchronized (lock) {
            return observers.contains(o);
        }
    }
    
    public void notifyObservers(EventObject e) {
        synchronized (lock) {
            for (Observer o : observers) {
                o.onNotify(e);
            }
        }
    }
}
