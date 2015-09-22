package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public abstract class Scene implements Displayable<DisplayableManager> {

    private DisplayableManager owner;
    private List<Pair<String, GameObject>> gameObjects;
    private Landscape Landscape;
    private Camera camera;
    private ScriptManager<Scene> scriptManager;

    public Scene() {
        this.camera = new Camera();
        this.Landscape = new Landscape();
        this.scriptManager = new ScriptManager<>();
        this.gameObjects = new ArrayList<>();
    }

    @Override
    public void init(DisplayableManager owner) {
        this.owner = owner;
        this.scriptManager.init(this);
        this.Landscape.init(this);
        this.camera.init(this);
        this.init();
        this.getGameObjects().stream().forEach(go -> go.init(this));
    }

    protected abstract void init();

    @Override
    public void load() {
        this.Landscape.load();
        this.camera.load();
        this.scriptManager.load();
        this.getGameObjects().stream().forEach(go -> go.load());
    }

    @Override
    public void update() {
        this.camera.update();
        this.getGameObjects().stream().forEach(go -> go.update());
    }

    @Override
    public void render(Graphics g) {
        camera.render(g);
        this.getGameObjects().stream().forEach(go -> go.render(g));
    }

    /**
     * Recupere l'id du GameObject
     *
     * @param id L'id du GameObject
     *
     * @return Le GameObject
     */
    public GameObject getGameObject(String id) {
        return getGameObjects().stream().filter(go -> go.getId().equals(id)).findFirst().orElse(null);
    }

    public void addGameObject(String id, GameObject go) {
        this.gameObjects.add(new Pair<String, GameObject>(id, go));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects.stream().map(p -> p.getValue()).collect(Collectors.toList());
    }

    public Iterator<GameObject> iterateOverGO() {
        return this.getGameObjects().iterator();
    }

    public Landscape getLandscape() {
        return this.Landscape;
    }

    public Camera getCamera() {
        return camera;
    }

    public void addScript(Script... scripts) {
        this.scriptManager.add(scripts);
    }
}
