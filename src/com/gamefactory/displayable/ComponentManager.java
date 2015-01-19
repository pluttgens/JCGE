package com.gamefactory.displayable;

import java.util.LinkedList;
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
public class ComponentManager {

    List<Component> components;

    public ComponentManager() {
        this.components = new LinkedList<>();
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
                components.add((Component) Class.forName("com.gamefactory.components." + componentName).newInstance());
            } catch (ClassNotFoundException ex) {
                throw new IllegalArgumentException("Le component " + componentName + " n'existe pas.");
            } catch (InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException("Le component " + componentName + " existe mais celui-ci n'a pu être instancié.", ex);
            }
        }
        components.sort(new Component.UpdatePriorityComparator());
    }

    // A modifier.
    /**public Method invoke() {
        for (Component component : components) {
            try {
                return (float) component.getClass().getMethod("getX").invoke(component);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            }
        }
        throw new NoSuchComponentException("Le Game Object ne contient pas de component position.");
    }**/

    
    
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
