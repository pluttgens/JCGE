/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.utils.audio;

import java.util.EventObject;

/**
 * AudioEvent permet d'identifier une requête effectuée au Moteur Audio/
 *
 * @author Pascal Luttgens
 */
public class AudioEvent extends EventObject {

    public static class Type {

        public static final Type PLAY = new Type("PLAY");
        public static final Type STOP = new Type("STOP");

        private Type(String name) {
            this.name = name;
        }

        private final String name;

    }

    private final Type type;
    private final AudioResource resource;
    private float volume;

    public AudioEvent(Object source, Type type, AudioResource resource) {
        super(source);
        this.type = type;
        this.resource = resource;
    }

    public AudioEvent(Object source, Type type, AudioResource resource, float volume) {
        super(source);
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

    public float getVolume() {
        return this.volume;
    }

}
