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

    private final String event;
    
    private final Object
    
    public Event(Object o, String event) {
        super(o);
        this.event = event;
    }
    
    public String getEvent() {
        return this.event;
    }
   
}
