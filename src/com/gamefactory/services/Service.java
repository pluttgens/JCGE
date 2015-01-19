package com.gamefactory.services;

import com.gamefactory.utils.events.Observer;

/**
 * Interface représentant tous les services. Chaque service est logiquement un
 * Observer car celui-ci doit être découplé du reste du jeu mais recevoir des
 * events.
 *
 * @author Pascal Luttgens.
 */
public interface Service extends Observer {

}
