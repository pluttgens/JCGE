/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.utils.audio;

import java.io.File;
import pjs4.gamefactory.services.Resource;

/**
 * Enumération asociant toutes les ressources audio du jeu à leur path.
 * 
 * @author Pascal Luttgens.
 */
public enum AudioResource implements Resource {
    TEST("test1.wav"),
    TEST2("test2.wav");
    
    private final String path;
    
    private AudioResource(String soundPath) {
        path = soundPath;
    }
    
    public File getAudioFile() {
        return new File(path);
    }

}
