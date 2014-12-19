/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.services;

/**
 *
 * @author scalpa
 */
public interface AudioService extends Service {

    void playSound(Resource sound);

    void stopSound(Resource sound);

    void stopAllSound();
}
