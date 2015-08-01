package com.gg.jcge.displayable;

import com.gg.jcge.displayable.scripts.ListenerScript;
import com.gg.jcge.displayable.scripts.LoadingScript;
import com.gg.jcge.displayable.scripts.UpdateScript;
import com.gg.jcge.game.Manager;
import com.gg.jcge.utils.events.Event;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class ScriptManager implements Manager<GameObject, Script> {

    private GameObject gameObject;

    private List<UpdateScript> scriptsUpdat;

    private List<LoadingScript> scriptsLoad;

    private List<ListenerScript> scriptsList;

    @Override
    public void init(GameObject gameObject) {
        this.gameObject = gameObject;
        this.scriptsUpdat = new ArrayList<>();
        this.scriptsLoad = new ArrayList<>();
        this.scriptsList = new ArrayList<>();

    }

    @Override
    public void load() {
        this.scriptsUpdat.stream().forEach(Script::load);
        this.scriptsList.stream().forEach(Script::load);

        this.scriptsLoad.stream().map(s -> {
            s.load();
            return s;
        }).forEach(LoadingScript::executeOnce);

        this.scriptsLoad.stream().close();
    }

    @Override
    public void update() {
        this.scriptsUpdat.stream().forEach(UpdateScript::execute);
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    @Override
    public void add(Script... script) {
        for (Script s : script) {
            if (s instanceof UpdateScript) {
                this.scriptsUpdat.add((UpdateScript) s);
            } else if (s instanceof LoadingScript) {
                this.scriptsLoad.add((LoadingScript) s);
            } else if (s instanceof ListenerScript) {
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
        this.scriptsList.stream().forEach(s -> {
            if (!e.isConsumed()) {
                s.onEvent(e);
            }
        });
    }

    public Component getComponent(Class<? extends Component> componentClass) {
        return this.gameObject.getComponentManager().getComponent(componentClass);
    }
}
