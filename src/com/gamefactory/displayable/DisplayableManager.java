package com.gamefactory.displayable;

import com.gamefactory.callbacks.Initiable;
import com.gamefactory.callbacks.Loadable;
import com.gamefactory.game.Displayable;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DisplayableManager implements Manager<Displayable, Scene> {

    private DisplayableManager owner;
    
    private List<Scene> cachedScenes;

    private List<Scene> activeScenes;

    public void init(DisplayableManager owner) {
        this.owner = owner;
        this.cachedScenes = new LinkedList<>();
        this.activeScenes = new ArrayList<>();
    }

    @Override
    public void load() {
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GameObject getGameObject(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(Scene... u) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void init(Displayable t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    

}
