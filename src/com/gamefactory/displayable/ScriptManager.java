package com.gamefactory.displayable;

import com.gamefactory.scripts.LoadingScript;
import com.gamefactory.scripts.UpdateScript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
public final class ScriptManager{
    
    private final GameObject owner;
    private final List<UpdateScript> scripts;
    
    public ScriptManager(GameObject owner) {
        this.owner = owner;
        this.scripts = new ArrayList<>();
    }

    /**
     * Initialise une liste de components.
     *
     * - Pascal Luttgens.
     *
     * @param scripts    Les scripts.
     *
     * @since 1.0
     */
    public void init(Script ... scripts) {
        
        this.scripts.addAll(Arrays.asList(components));
        
        this.components.sort(new Component.UpdatePriorityComparator());
        this.components.stream().forEach(c -> c.init(this));
        
    }

    /**
     * Initialise tous les components
     */
    public void load() {
        this.scripts.stream().forEach(s -> s.execute());   
    }
    
    public void update() {
        this.scripts.stream().map(s -> {
            s.updateLogic();
            return s;
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
    
    public GameObject getGameObject() {
        return owner;
    }
    
}