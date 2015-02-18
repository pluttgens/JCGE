package com.gamefactory.components;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.GameObject;
import java.util.ArrayList;
import java.util.Iterator;

public class Collider extends Component {

    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<GameObject> gameObjectsCollider = new ArrayList<>();
    
    @Override
    public void init(ComponentManager owner) {
        super.init(owner);
        gameObjects = owner.getScene().getGameObjects();
        
        Iterator<GameObject> it = gameObjects.iterator();
        while (it.hasNext()) {
            if(it.next().getComponentManager().checkForComponent("Collider")){
             gameObjectsCollider.add(it.next());               
            }
        }
    }

    @Override
    public void update() {
      Iterator<GameObject> it = gameObjectsCollider.iterator();
            while (it.hasNext()) {
                /*Position p = it.next().getComponentManager().;
                if(){*/
                    
                }         
            }
        }
