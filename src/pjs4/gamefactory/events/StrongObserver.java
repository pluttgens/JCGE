/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.events;

/**
 *
 * @author scalpa
 */
public interface StrongObserver extends Observer {
    
    void onNotify(Object subject, Event event);
    
}
