package com.gamefactory.displayable;

import com.gamefactory.components.Collider;
import com.gamefactory.displayable.gameobjects.Hero;
import com.gamefactory.displayable.gameobjects.Obstacle;
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
        Obstacle obstacle = new Obstacle();
        gameObjects.put(hero.id, hero);
        gameObjects.put(treasure.id, treasure);
        gameObjects.put(obstacle.id, obstacle);
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
        
        
        
        // Je met ici mais à déplacer, crée dépendance Scene <-> Collider
        it = gameObjects.values().iterator();
        while (it.hasNext()) {
            GameObject next = it.next();
            try {
	        	Collider col  = (Collider) next.getComponentManager().getComponent("Collider");
	
	            Iterator<GameObject> it2 = gameObjects.values().iterator();
	            while (it2.hasNext()) {
	                GameObject next2 = it2.next();
	
	                if(next != next2) {
	                	try {
	                		Collider col2  = (Collider) next2.getComponentManager().getComponent("Collider");
		                	if (col.getX() < col2.getX() + col2.getWidth() &&
		        			   col.getX() + col.getWidth() > col2.getX() &&
		        			   col.getY() < col2.getY() + col2.getHeight() &&
		        			   col.getHeight() + col.getY() > col2.getY()) {
		        			    next2.onEnterCollision(next.id);
		        			    next.onEnterCollision(next2.id);
		                	}
	                	}
		                catch(java.lang.IllegalStateException e) {
		                	// next2 n'a pas de Collider
	                	}
	        		}
	            }
	                
            }
            catch( java.lang.IllegalStateException e) {
            	// next n'a pas de Collider
            }
            
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
