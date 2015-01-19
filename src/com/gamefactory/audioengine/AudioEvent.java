package com.gamefactory.audioengine;

import java.util.EventObject;

/**
 * AudioEvent permet d'identifier une requête effectuée au Moteur Audio.
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
    
    private String id;
    private final Type type;
    private final String audioAsset;
    private float volume = 0;

    public AudioEvent(Object source, Type type, String audioAsset) {
        super(source);
        this.type = type;
        this.audioAsset = audioAsset;
    }

    public AudioEvent(Object source, Type type, String audioAsset, String id) {
        super(source);
        this.type = type;
        this.audioAsset = audioAsset;
        this.id = id;
    }

    public Type getType() {
        return this.type;
    }

    public String getAsset() {
        return this.audioAsset;
    }

    public float getVolume() {
        return this.volume;
    }
    
    public String getId() {
        return audioAsset +(id == null ? "" :  "_" + id);
    }

}
