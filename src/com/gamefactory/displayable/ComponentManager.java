package com.gamefactory.displayable;

import com.gamefactory.components.Renderer;
import com.gamefactory.game.Displayable;
import com.gamefactory.scripts.UpdateScript;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Le Component Manager encapsule le comportement des components au sein d'un
 * game Object.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public final class ComponentManager implements Manager<GameObject, Component>{
    
    private  GameObject owner;
    private  List<Component> components;
    
    @Override
    public void init(GameObject owner) {
        this.owner = owner;
        this.components = new ArrayList<>();
    }

    /**
     * Initialise une liste de components.
     *
     * - Pascal Luttgens.
     *
     * @param components Les components.
     * @param scripts    Les scripts.
     *
     * @since 1.0
     */
    @Override
    public void add(Component ... components) {
        
        this.components.addAll(Arrays.asList(components));
        
        this.components.sort(new Component.UpdatePriorityComparator());
        this.components.stream().forEach(c -> c.init(this));
        
    }

    /**
     * Initialise tous les components
     */
    public void load() {
        this.components.stream().forEach(c -> c.load());
        
    }
    
    public void update() {
        this.components.stream().map(c -> {
            c.updateLogic();
            return c;
        }).forEach(c -> c.updateComponent());
    }

// A modifier.
    /**
     * public Method invoke() { for (Component component : components) { try {
     * return (float) component.getClass().getMethod("getX").invoke(component);
     * } catch (NoSuchMethodException | SecurityException |
     * IllegalAccessException | IllegalArgumentException |
     * InvocationTargetException ex) { } } throw new
     * NoSuchComponentException("Le Game Object ne contient pas de component
     * position."); }*
     */
    public Component getComponent(String componentName) {
        Iterator<Component> it = components.iterator();
        while (it.hasNext()) {
            Component component = it.next();
            if (component.getClass().getSimpleName().equals(componentName)) {
                return component;
            }
        }
        throw new IllegalStateException("Component manquant : " + componentName);
    }
    
    public Component getComponent(Class<? extends Component> componentClass) {
        Iterator<Component> it = components.iterator();
        while (it.hasNext()) {
            Component component = it.next();
            if (component.getClass().equals(componentClass)) {
                return component;
            }
        }
        throw new IllegalStateException("Component manquant : " + componentClass.getSimpleName());
    }

    /**
     * Vérifie si un component est initialisé dans la liste à partir de son nom.
     *
     * - Pascal Luttgens.
     *
     * @param componentName Le nom du component.
     *
     * @return True si le component fait partie de la liste.
     *
     * @since 1.0
     */
    public boolean checkForComponent(String componentName) {
        Iterator<Component> it = components.iterator();
        while (it.hasNext()) {
            Component component = it.next();
            if (component.getClass().getSimpleName().equals(componentName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si un component est initialisé à partir d'une représentation de
     * sa classe.
     *
     * - Pascal Luttgens.
     *
     * @param componentClass Un objet class représentant le component.
     *
     * @return True si le component fait partie de la liste.
     *
     * @since 1.0
     */
    public boolean checkForComponent(Class<? extends Component> componentClass) {
        Iterator<Component> it = components.iterator();
        while (it.hasNext()) {
            Component component = it.next();
            if (component.getClass().equals(componentClass)) {
                return true;
            }
        }
        return false;
    }
    
    public Component getComponentFromGO(String id, Class<? extends Component> componentClass) {
        return this.owner.getOwner().getGameObject(id).getComponentManager().getComponent(componentClass);
    }

    /**
     * Retourne la Scène
     *
     */
    public Scene getScene() {
        return this.owner.getOwner();
    }
    
    public GameObject getOwner() {
        return owner;
    }

    @Override
    public void render(Graphics g) {
        Renderer renderer = (Renderer) getComponent("Renderer");
        try {
            renderer.getClass().getMethod("render", Graphics.class).invoke(renderer, g);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GameObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GameObject getGameObject(String id) {
        return this.owner.getOwner().getGameObject(id);
    }
    
}
