/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.events;

/**
 * Un observer fort est un observer qui a besoin d'une référence sur l'objet
 * auteur de l'event en plus de l'event lui-même.
 *
 * @author Pascal Luttgens.
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface StrongObserver extends Observer {

    /**
     * Passe à l'observer l'objet auteur de l'event et l'event.
     *
     * - Pascal Luttgens.
     * @param subject L'objet auteur de l'event.
     * @param event   L'évent.
     */
    void onNotify(Object subject, Event event);

}
