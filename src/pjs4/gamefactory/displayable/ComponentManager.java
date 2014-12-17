/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.displayable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Le Component Manager encapsule le comportement des components au sein d'un
 * game Object.
 *
 * @author Pascal Luttgens
 */
public class ComponentManager {

    List<Component> components;

    public ComponentManager() {
        this.components = new ArrayList<>();
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
    public void init(String[] componentNames) {
        for (String componentName : componentNames) {
            try {
                components.add((Component) Class.forName("pjs4.gamefactory.components." + componentName).newInstance());
            } catch (ClassNotFoundException ex) {
                throw new IllegalArgumentException("Le component " + componentName + " n'existe pas.");
            } catch (InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException("Le component existe mais celui-ci n'a pu être instancié.", ex);
            }
        }
    }

    // A modifier.
    public float getPosition() {
        for (Component component : components) {
            try {
                return (float) component.getClass().getMethod("getX").invoke(component);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            }
        }
        throw new NoSuchComponentException("Le Game Object ne contient pas de component position.");
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
        for (Component component : components) {
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
        for (Component component : components) {
            if (component.getClass() == componentClass) {
              return true;
          }
        }
        return false;
    }
}
