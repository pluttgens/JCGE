/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.audioengine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import pjs4.gamefactory.utils.events.Notifier;

/**
 *
 * @author scalpa
 */
public class AudioEngineTest {

    @Test
    public void test() {
        try {
            AudioEngine ae = new AudioEngine();
            ae.start();
            
            Notifier n = new Notifier();
            n.registerObserver(ae);
            n.notifyObservers(new AudioEvent(ae, AudioEvent.Type.PLAY, "test1"));
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AudioEngineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
