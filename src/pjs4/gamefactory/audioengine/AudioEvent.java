/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.audioengine;

import pjs4.gamefactory.events.Event;

/**
 *
 * @author scalpa
 */
public class AudioEvent implements Event {
    
    public static class Type {
        public static final Type PLAY = new Type("PLAY");
        public static final Type STOP = new Type("STOP");
        
        private Type(String name) {
            this.name = name;
        }
        
        private final String name;
    }
    
    private Type type;
    private final AudioResource resource;
    private float volume;
    
    public AudioEvent(Type type, AudioResource resource) {
        this.type = type;
        this.resource = resource;
    }
    
        public AudioEvent(Type type, AudioResource resource, float volume) {
        this.type = type;
        this.resource = resource;
        this.volume = volume;
    }
    
    public Type getType() {
        return this.type;
    }
    public AudioResource getResource() {
        return this.resource;
    }
    
}
