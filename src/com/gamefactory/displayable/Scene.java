package com.gamefactory.displayable;

import com.gamefactory.displayable.gameobjects.Hero;
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
        gameObjects.put(hero.id, hero);
    }

    public void init() {
        Iterator<GameObject> it = gameObjects.values().iterator();
        while (it.hasNext()) {
            GameObject next = it.next();
            next.init();
            next.setScene(this);
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

}
