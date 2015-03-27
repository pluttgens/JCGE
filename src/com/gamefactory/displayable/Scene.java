package com.gamefactory.displayable;

import com.gamefactory.displayable.gameobjects.EmptyGameObject;
import com.gamefactory.game.Displayable;
import com.gamefactory.scripts.InitialPosition;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
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
        this.getGameObjects().stream().forEach(go -> go.init(this));
    }

    @Override
    public void update() {
        this.camera.update();

        Iterator<GameObject> it = this.iterateOverGO();
        while (it.hasNext()) {
            GameObject next = it.next();
            next.update();
        }
    }

    @Override
    public void render(Graphics g) {
        camera.render(g);
        Iterator<GameObject> it = this.iterateOverGO();
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
        GameObject ret = new EmptyGameObject();
        for (int i = 0 ; i < gameObjects.size() ; i++){
            if (gameObjects.get(i).getKey().equals(id));
                ret = gameObjects.get(i).getValue();
        }
        return ret;
    }

    /**
     * Retourne l'ensemble des GameObjects contenu dans la scene
     *
     * @return L'ensemble des GameObjects
     */
    /*public ArrayList<GameObject> getGameObjects() {
        return new ArrayList(gameObjects.values());
    }

    public Iterator<GameObject> iterateOverGO() {
        return this.gameObjects.values().iterator();
    }

    
    public void addGameObject(String id, GameObject go) {
        this.gameObjects.put(id, go);
    }*/
    
    //Gameobjects dans la liste
    
    public void addGameObject(String id, GameObject go){
        Pair<String, GameObject> p = new Pair(id,go); 
        this.gameObjects.add(p);
    }
    
    public ArrayList<GameObject> getGameObjects() {
        ArrayList<GameObject> p = new ArrayList();
        for( int i = 0 ; i < gameObjects.size() ; i ++){
            p.add(gameObjects.get(i).getValue());
        }
        return p;
    }

    public ListIterator<GameObject> iterateOverGO() {
        return this.getGameObjects().listIterator();
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
