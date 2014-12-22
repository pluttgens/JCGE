/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.services;

import pjs4.gamefactory.events.Observer;

/**
 * Interface représentant tous les services. Chaque service est logiquement un
 * Observer car celui-ci doit être découplé du reste du jeu mais recevoir des
 * events.
 *
 * @author Pascal Luttgens.
 */
public interface Service extends Observer {

}
