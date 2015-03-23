package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import com.gamefactory.scripts.InitialPosition;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public abstract class Scene implements Displayable<DisplayableManager> {

    private DisplayableManager owner;
    private Map<String, GameObject> gameObjects;
    private Landscape Landscape;
    private Camera camera;
    private ScriptManager<Scene> scriptManager;

    public Scene() {
        this.camera = new Camera();
        this.Landscape = new Landscape();
        this.gameObjects = new HashMap<>();
        this.scriptManager = new ScriptManager<>();       

    }

    @Override
    public void init(DisplayableManager owner) {
        this.scriptManager.init(this);
        this.Landscape.init(this);
        this.camera.init(this);
        this.init();
        this.gameObjects.values().stream().forEach(go -> go.init(this));
    }
    
    protected abstract void init();

    @Override
    public void load() {
        this.Landscape.load();
        this.camera.load();
        this.scriptManager.load();
        this.gameObjects.values().stream().forEach(go -> go.load());
    }

    @Override
    public void update() {
        this.camera.update();

        Iterator<GameObject> it = gameObjects.values().iterator();
        while (it.hasNext()) {
            GameObject next = it.next();
            next.update();
        }

    }

    @Override
    public void render(Graphics g) {
        camera.render(g);
        Iterator<GameObject> it = gameObjects.values().iterator();
        while (it.hasNext()) {
            GameObject next = it.next();
            next.render(g);
        }
    }

    /**
     * Recupere l'id du GameObject
     *
     * @param id L'id du GameObject
     *
     * @return Le GameObject
     */
    public GameObject getGameObject(String id) {
        GameObject ret = this.gameObjects.get(id);
        return ret;
    }

    /**
     * Retourne l'ensemble des GameObjects contenu dans la scene
     *
     * @return L'ensemble des GameObjects
     */
    public ArrayList<GameObject> getGameObjects() {
        return new ArrayList(gameObjects.values());
    }

    public Iterator<GameObject> iterateOverGO() {
        return this.gameObjects.values().iterator();
    }

    public Landscape getLandscape() {
        return this.Landscape;
    }

    public Camera getCamera() {
        return camera;
    }

    public void addGameObject(String id, GameObject go) {
        this.gameObjects.put(id, go);
    }

    public void addScript(Script... scripts) {
        this.scriptManager.add(scripts);
    }
}
