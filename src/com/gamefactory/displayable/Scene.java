package com.gamefactory.displayable;

import java.util.List;
import com.gamefactory.game.Displayable;
import java.awt.Graphics;
import java.util.ArrayList;

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

    private List<GameObject> gameObjects = new ArrayList<>();
    private Landscape Landscape = new Landscape();

    public Scene() {
        gameObjects.add(new GameObjectTest());
    }

    @Override
    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    @Override
    public void render(Graphics g) {
        Landscape.render(g);
        for (GameObject gameObject : gameObjects) {
            gameObject.render(g);
        }
    }

}
