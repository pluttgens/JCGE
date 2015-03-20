package com.gamefactory.displayable;

import com.gamefactory.scripts.LoadingScript;
import com.gamefactory.scripts.UpdateScript;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;

public final class ScriptManager <T extends Manager> implements Manager<T, Script>{
    
    private  T owner;
    
    private final List<UpdateScript> scriptsU;

    private final List<LoadingScript> scriptsL;
    
    public ScriptManager(T owner) {
        this.owner = owner;
        this.scriptsU = new ArrayList<>();
        this.scriptsL = new ArrayList<>();
    }

   

    @Override
    public void load() {
        this.scriptsU.stream().forEach(s -> s.load());   
        this.scriptsL.stream().map(s ->{s.load(); return s;}).forEach(s ->s.execute());
        
        this.scriptsL.stream().close();
    }
    
    @Override
    public void update() {
        this.scriptsU.stream().forEach(s -> s.execute());   
    }  
    
    public T getOwner() {
        return owner;
    }

    @Override
    public GameObject getGameObject(String id) {
        return this.owner.getGameObject(id);
    }

    @Override
    public void add(Script... script) {
      for(Script s : script){
            if ( s instanceof UpdateScript){
                this.scriptsU.add((UpdateScript) s);
            }
            else{
                this.scriptsL.add((LoadingScript) s);
            }
        }
        this.scriptsU.stream().forEach(s -> s.init(this));
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public void init(T t) {
        this.owner = t;
    }
}