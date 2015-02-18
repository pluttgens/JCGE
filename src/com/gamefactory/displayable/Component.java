/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.utils.events.Notifier;
import com.gamefactory.utils.events.Observer;
import com.gamefactory.utils.events.Subject;
import java.util.Comparator;

/**
 * Un component encapsule une fonctionalité d'un game object.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public abstract class Component /**implements Observer, Subject **/{

    protected ComponentManager owner;

    private final Notifier notifier;

    public Component() {
        this.notifier = new Notifier(this);
    }

    /**
     * Initialise le component encapsulant 
     * une fonctionnalit� d'un game object
     * @param owner
     */
    public void init(ComponentManager owner) {
        this.owner = owner;
    }

    public void update() {

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

}
