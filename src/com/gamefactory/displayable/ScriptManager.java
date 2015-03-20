package com.gamefactory.displayable;

import com.gamefactory.scripts.LoadingScript;
import com.gamefactory.scripts.UpdateScript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class ScriptManager <T extends Manager, Script> implements Manager<T>{
    
    private final T owner;
    
    private final List<UpdateScript> scriptsU;

    private final List<LoadingScript> scriptsL;
    
    public ScriptManager(T owner) {
        this.owner = owner;
        this.scriptsU = new ArrayList<>();
        this.scriptsL = new ArrayList<>();
    }

    public void init(Script ... scripts){
        for(Script s : scripts){
            if ( s instanceof UpdateScript){
                this.scriptsU.add((UpdateScript) s);
            }
            else{
                this.scriptsL.add((LoadingScript) s);
            }
        }
        this.scriptsU.stream().forEach(s -> s.init(this));
        this.scriptsL.stream().forEach(s -> s.init(this));
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
}