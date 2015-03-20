package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DisplayableManager implements Manager<Displayable, Scene> {

    private DisplayableManager owner = null;

    private List<Scene> cachedScenes;

    private List<Scene> activeScenes;

    @Override
    public void load() {
        this.activeScenes.stream().forEach(s -> s.load());
    }

    @Override
    public void render(Graphics g) {
        for (Scene s : activeScenes) {
            s.render(g);
        }

    }

    @Override
    public void update() {
        for (Scene s : activeScenes) {
            s.update();
        }
    }

    @Override
    public void add(Scene... u) {

        activeScenes.addAll(Arrays.asList(u));

    }

    @Override
    public void init(Displayable t) {
        this.owner = owner;
        this.cachedScenes = new LinkedList<>();
        this.activeScenes = new ArrayList<>();
        activeScenes.add(new Scene());
        this.activeScenes.stream().forEach(s -> s.init(this));
    }

}
