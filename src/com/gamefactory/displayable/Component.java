/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.callbacks.game.Callbacks;
import com.gamefactory.listeners.ComponentListener;
import com.gamefactory.scripts.LoadingScript;
import com.gamefactory.scripts.UpdateScript;
import com.gamefactory.utils.events.Notifier;
import com.sun.org.apache.xml.internal.security.exceptions.AlgorithmAlreadyRegisteredException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Un component encapsule une fonctionalité d'un game object.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public abstract class Component implements Callbacks<ComponentManager>/**
 * implements Observer, Subject *
 */
{
    
    protected ComponentManager owner;
    
    private final Notifier notifier;
    
    private final List<Listener<Component, Void>> listeners;
    
    public Component() {
        this.notifier = new Notifier(this);
        this.listeners = new ArrayList<>();
    }

    public void addListeners(ComponentListener[] listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
    }

    
    @Override
    public final void init(ComponentManager cm) {
        this.owner = cm;
    }
    
    
    public static final Component build(Class<? extends Component> clazz, Script<Component>[] scripts, ComponentListener[] listeners) {
        Component c = null;
        try {
            c = clazz.newInstance();
            if (listeners != null) {
                c.addListeners(listeners);
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Component.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    public void updateLogic() {
        
    }
    
    public void updateComponent() {
        
    }

    /**
     * Retourne l'indice de priorité permettant de déterminer à quel moment la
     * méthode update du component va etre appelée par rapport aux autres
     * components faisant parti d'une même liste.
     *
     * - Pascal Luttgens.
     *
     * @return L'indice de priorité.
     */
    protected int getUpdatePriority() {
        return 0;
    }

    /**
     * Comparateur de component. Permet de déterminer l'ordre dans lequel les
     * components sont triés dans une liste afin d'être cohérent dans l'ordre
     * d'update.
     *
     *
     * @author Pascal Luttgens
     *
     * @version 1.0
     *
     * @since 1.0
     */
    public static class UpdatePriorityComparator implements Comparator<Component> {
        
        @Override
        public int compare(Component c1, Component c2) {
            if (c1.getUpdatePriority() == c2.getUpdatePriority()) {
                return 0;
            } else if (c1.getUpdatePriority() < c2.getUpdatePriority()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    //@Override
    public Notifier getNotifier() {
        return this.notifier;
    }
    
    public ComponentManager getComponentManager() {
        return this.owner;
    }
    
}
