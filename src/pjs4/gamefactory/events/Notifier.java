/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.events;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author scalpa
 */
public class Notifier<T> {

    private final Object lock;

    private final T subject;
    private List<Observer> observers;

    public Notifier(T subject) {
        this.observers = new LinkedList<>();
        this.lock = new Object();
        this.subject = subject;
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
    
    public void notifyObservers(Event e) {
        synchronized (lock) {
            for (Observer o : observers) {
                try {
                    WeakObserver wo = (WeakObserver) o;
                    wo.onNotify(e);
                } catch (ClassCastException ex) {
                    try {
                        StrongObserver so = (StrongObserver) o;
                        so.onNotify(subject, e);
                    } catch (ClassCastException ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
        }
    }
    
    
    
}
