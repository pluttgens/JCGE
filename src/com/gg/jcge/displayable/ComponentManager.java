package com.gg.jcge.displayable;

import com.gg.jcge.components.Renderer;
import com.gg.jcge.game.Manager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

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
public final class ComponentManager implements Manager<GameObject, Component> {

    private GameObject gameObject;
    private List<Component> components;

    @Override
    public void init(GameObject gameObject) {
        this.gameObject = gameObject;
        this.components = new ArrayList<>();
    }

    /**
     * Initialise une liste de components.
     *
     * - Pascal Luttgens.
     *
     * @param components Les components
     *
     * @since 1.0
     */
    @Override
    public void add(Component... components) {

        this.components.addAll(Arrays.asList(components));

        this.components.sort(new Component.UpdatePriorityComparator());
        this.components.stream().forEach(c -> c.init(this));

    }

    /**
     * Initialise tous les components
     */
    @Override
    public void load() {
        this.components.stream().forEach(Component::load);
    }

    @Override
    public void update() {
        this.components.stream().forEach(Component::update);
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
        return null;
    }

    public Component getComponent(Class<? extends Component> componentClass) {
        components.stream().filter(component -> component.getClass().isInstance(componentClass)).findFirst().orElse(null);

        return components.stream().filter(c -> {
            return c.getClass().isInstance(componentClass);
        }).findFirst().orElse(null);
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
        return getComponent(componentClass) != null;
    }

    public Component getComponentFromGO(String id, Class<? extends Component> componentClass) {
        return this.gameObject.getScene().getGameObject(id).getComponentManager().getComponent(componentClass);
    }

    /**
     * Retourne la Scène
     *
     */
    public Scene getScene() {
        return this.gameObject.getScene();
    }

    public GameObject getgameObject() {
        return gameObject;
    }

    @Override
    public void render(Graphics g) {
        Renderer renderer = (Renderer) getComponent(Component.RenderComponent.class);
        if (renderer != null) {
            renderer.render(g);
        }
    }

}
