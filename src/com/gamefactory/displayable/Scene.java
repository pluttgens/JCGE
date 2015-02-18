package com.gamefactory.displayable;

import com.gamefactory.displayable.gameobjects.Hero;
import com.gamefactory.displayable.gameobjects.Treasure;
import com.gamefactory.game.Displayable;
import java.awt.Graphics;
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
public class Scene implements Displayable {

    private Map<String, GameObject> gameObjects = new HashMap<>();
    private Landscape Landscape = new Landscape();

    public Scene() {
        GameObject hero = new Hero();
        Treasure treasure = new Treasure();
        gameObjects.put(hero.id, hero);
        gameObjects.put(treasure.id, treasure);
    }

    @Override
    public void init() {
        Iterator<GameObject> it = gameObjects.values().iterator();
        while (it.hasNext()) {
            GameObject next = it.next();
            next.setScene(this);
            next.init();
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
        Iterator<GameObject> it = gameObjects.values().iterator();
        while (it.hasNext()) {
            GameObject next = it.next();
            next.update();
        }
    }

    @Override
    public void render(Graphics g) {
        Landscape.render(g);
        Iterator<GameObject> it = gameObjects.values().iterator();
        while (it.hasNext()) {
            GameObject next = it.next();
            next.render(g);
        }
    }
    
    /**
     * Recupere l'id du GameObject
     * @param id
     * @return
     */
    public GameObject getGameObject(String id) {
        GameObject ret = this.gameObjects.get(id);
        return ret;
    }
    
}
