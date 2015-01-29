package com.gamefactory.displayable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
public class ComponentManager {

    private Map<String, Component> components;

    public ComponentManager() {
        this.components = new LinkedHashMap<>();
    }

    /**
     * Initialise une liste de component à partir de leur noms. Les components à
     * initialiser doivent posseder un constructeur vide.
     *
     * - Pascal Luttgens.
     *
     * @param componentNames Les noms des components.
     *
     * @since 1.0
     */
    public void init(String... componentNames) {
        final List<Component> componentsToSort = new ArrayList<>();
        for (String componentName : componentNames) {
            try {
                componentsToSort.add((Component) Class.forName("com.gamefactory.components." + componentName).getConstructor(this.getClass()).newInstance(this));
            } catch (ClassNotFoundException ex) {
                throw new IllegalArgumentException("Le component " + componentName + " n'existe pas.");
            } catch (InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException("Le component " + componentName + " existe mais celui-ci n'a pu être instancié.", ex);
            } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(ComponentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        componentsToSort.sort(new Component.UpdatePriorityComparator());
        for (int i = 0; i < componentNames.length; ++i) {
            components.put(componentNames[i], componentsToSort.get(i));
        }

        Iterator<Component> it = components.values().iterator();
        while (it.hasNext()) {
            it.next().init();
        }

    }

    public void update() {
        Iterator<Component> it = components.values().iterator();
        while (it.hasNext()) {
            it.next().update();
        }
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
        for (Map.Entry<String, Component> entrySet : components.entrySet()) {
            String key = entrySet.getKey();
            Component value = entrySet.getValue();
            if (key.equals(componentName)) {
                return value;
            }
        }
        throw new IllegalStateException("Component manquant : " + componentName);
    }

    public Component getComponent(Class<? extends Component> componentClass) {
        Iterator<Component> it = components.values().iterator();
        while (it.hasNext()) {
            Component next = it.next();
            if (next.getClass().equals(componentClass)) {
                return next;
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
        Iterator<String> it = components.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.equals(componentName)) {
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
        Iterator<Component> it = components.values().iterator();
        while (it.hasNext()) {
            Component next = it.next();
            if (next.getClass().equals(componentClass)) {
                return true;
            }
        }
        return false;
    }
}
