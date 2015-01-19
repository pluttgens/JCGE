package com.gamefactory.audioengine;

import com.gamefactory.assets.assetmanager.AssetManager;
import com.gamefactory.services.ServiceLocator;
import org.junit.Test;
import com.gamefactory.utils.events.Notifier;

/**
 *
 * @author Pascal Luttgens
 */
public class AudioEngineTest {

    @Test
    public void test() {
        ServiceLocator.provideAssetManager(new AssetManager());
        AudioEngine ae = new AudioEngine();
        ae.start();
        
        Notifier n = new Notifier();
        n.registerObserver(ae);
        n.notifyObservers(new AudioEvent(ae, AudioEvent.Type.PLAY, "test1.wav"));
    }

}
