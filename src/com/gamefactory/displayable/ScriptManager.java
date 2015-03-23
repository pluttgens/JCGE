package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import com.gamefactory.scripts.ListenerScript;
import com.gamefactory.scripts.LoadingScript;
import com.gamefactory.scripts.UpdateScript;
import com.gamefactory.utils.events.Event;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public final class ScriptManager<T extends Displayable> implements Manager<T, Script> {

    private T owner;

    private List<UpdateScript> scriptsUpdat;

    private List<LoadingScript> scriptsLoad;

    private  List<ListenerScript> scriptsList;

    @Override
    public void init(T owner) {
        this.owner = owner;
        this.scriptsUpdat = new ArrayList<>();
        this.scriptsLoad = new ArrayList<>();
        this.scriptsList = new ArrayList<>();
        
        
    }

    @Override
    public void load() {
        this.scriptsUpdat.stream().forEach(s -> s.load());
        this.scriptsList.stream().forEach(s -> s.load());

        this.scriptsLoad.stream().map(s -> {
            s.load();
            return s;
        }).forEach(s -> s.executeOnce());

        this.scriptsLoad.stream().close();
    }

    @Override
    public void update() {
        this.scriptsUpdat.stream().forEach(s -> s.execute());
    }

    public T getOwner() {
        return owner;
    }

    @Override
    public void add(Script... script) {
        for (Script s : script) {
            if (s instanceof UpdateScript) {
                this.scriptsUpdat.add((UpdateScript) s);
            } else if (s instanceof LoadingScript) {
                this.scriptsLoad.add((LoadingScript) s);
            } else if (s instanceof ListenerScript){
                this.scriptsList.add((ListenerScript) s);
            }
        }
        this.scriptsLoad.stream().forEach(s -> s.init(this));
        this.scriptsUpdat.stream().forEach(s -> s.init(this));
        this.scriptsList.stream().forEach(s -> s.init(this));
    }

    @Override
    public void render(Graphics g) {
    }
    
    public void fireEvent(Event e) {
        this.scriptsList.stream().forEach(s -> s.onEvent(e));
    }
}
