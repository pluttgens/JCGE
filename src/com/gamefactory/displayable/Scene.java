package com.gamefactory.displayable;

import com.gamefactory.callbacks.Initiable;
import com.gamefactory.callbacks.Loadable;
import com.gamefactory.displayable.gameobjects.Hero;
import com.gamefactory.displayable.gameobjects.Obstacle;
import com.gamefactory.displayable.gameobjects.Treasure;
import com.gamefactory.game.Displayable;
import com.gamefactory.scripts.InitialPosition;
import com.gamefactory.scripts.LoadingScript;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class Scene implements Displayable<DisplayableManager> {

    private DisplayableManager owner;
    private Map<String, GameObject> gameObjects = new HashMap<>();
    private Landscape Landscape = new Landscape();
    private Camera camera;
    private List<LoadingScript> scripts;

    public Scene() {
    }

    @Override
    public void init(DisplayableManager owner) {
        this.camera = new Camera();
        this.scripts = new ArrayList<>();
        
        GameObject hero = new Hero();
        Treasure treasure = new Treasure();
        Obstacle obstacle = new Obstacle();

        gameObjects.put(hero.id, hero);
        gameObjects.put(treasure.id, treasure);
        gameObjects.put(obstacle.id, obstacle);

        this.scripts.add(new InitialPosition());
        
        this.Landscape.init(this);
        this.camera.init(this);
        
        this.scripts.stream().forEach(s -> s.init(this));
        this.gameObjects.values().stream().forEach(go -> go.init(this));
                
    }
    
    

    @Override
    public void load() {
        this.Landscape.load();
        this.camera.load();
        this.gameObjects.values().stream().forEach(new Loadable.ConsumerImpl());
        this.scripts.stream().forEach(s -> s.execute());
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
    
    

}
