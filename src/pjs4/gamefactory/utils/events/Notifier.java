/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.utils.events;

import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

/**
 * Un notifier est un objet que les classe subject doivent contenir afin de
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
public class Notifier implements Subject {
    
    private final Object lock;
    
    private List<Observer> observers;
    
    public Notifier() {
        this.observers = new LinkedList<>();
        this.lock = new Object();
    }
    
    @Override
    public void registerObserver(Observer o) {
        synchronized (lock) {
            if (!observers.contains(o)) {
                observers.add(o);
            }
        }
    }
    
    @Override
    public void unregisterObserver(Observer o) {
        synchronized (lock) {
            observers.remove(o);
        }
    }
    
    @Override
    public boolean isRegistered(Observer o) {
        synchronized (lock) {
            return observers.contains(o);
        }
    }
    
    @Override
    public void notifyObservers(EventObject e) {
        synchronized (lock) {
            for (Observer o : observers) {
                o.onNotify(e);
            }
        }
    }
}
