/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.utils.events;

/**
 * L'interface observer représente tous les observers, c'est a dire les classes
 * qui attendent des events de la part de code qui leur est inconnu.
 *
 * @author Pascal Luttgens.
 *
 * @version 1.0
 *
 * @since 1.0
 */
public interface Observer {
    /**
     * Passe l'event à l'observer
     *
     * -Pascal Luttgens.
     *
     * @param event L'event
     */
    void onNotify(Event event);
}
