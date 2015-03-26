/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.utils.events;

import java.util.EventObject;

/**
 *
 * @author scalpa
 */
public class Event extends EventObject {

    private boolean consumed;
    private final String event;
    private final Object message;
    
    
    public Event(Object source, String event) {
        this(source, event, null);
    }
    
    public Event(Object source, String event, Object message) {
        super(source);
        this.event = event;
        this.message = message;
        this.consumed = false;
    }
    
    
    public String getEvent() {
        return this.event;
    }
    
    public Object getMessage() {
        return this.message;
    }
    
    public boolean hasMessage() {
        return this.message != null;
    }
    
    public void consume() {
        this.consumed = true;
    }
    
    public boolean isConsumed() {
        return this.consumed;
    }
}
