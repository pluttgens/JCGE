package com.gamefactory.displayable;

import com.gamefactory.displayable.gameobjects.Hero;
import com.gamefactory.displayable.gameobjects.Obstacle;
import com.gamefactory.displayable.gameobjects.Treasure;
import com.gamefactory.game.Displayable;
import com.gamefactory.scripts.CameraScript;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
public class Scene implements Displayable {

    private Map<String, GameObject> gameObjects = new HashMap<>();
    private Landscape Landscape = new Landscape();
    private Camera camera;
    private List<Script> scripts;

    public Scene() {
        this.camera = new Camera(this);
        this.scripts = new ArrayList<>();
        GameObject hero = new Hero();
        Treasure treasure = new Treasure();
        Obstacle obstacle = new Obstacle();
        gameObjects.put(hero.id, hero);
        gameObjects.put(treasure.id, treasure);
        gameObjects.put(obstacle.id, obstacle);
    }

    @Override
    public void onLoading() {
        this.Landscape.onLoading();
        this.camera.onLoading();
        this.scripts.add(new CameraScript());
        Iterator<GameObject> it = gameObjects.values().iterator();
        while (it.hasNext()) {
            GameObject next = it.next();
            next.setScene(this);
            next.onLoading();
        }
        it = gameObjects.values().iterator();
        while (it.hasNext()) {
            GameObject next = it.next();
            next.setScene(this);
            next.getComponentManager().initComponents();
        }
    }

    @Override
    public void update() {
        this.camera.update();
        Iterator<Script> itScript = scripts.iterator();
        while (itScript.hasNext()) {
            Script next = itScript.next();
            next.update();
        }
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

}
