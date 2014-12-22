/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.events;

/**
 * Un observer faible est un observer qui a seulement besoin de l'event.
 *
 * @author Pascal Luttgens
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface WeakObserver extends Observer {

    /**
     * Passe l'event à l'observer
     *
     * -Pascal Luttgens.
     *
     * @param event L'event
     */
    void onNotify(Event event);

}
