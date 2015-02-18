package com.gamefactory.components;

import com.gamefactory.audioengine.AudioEngine;
import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.services.ServiceLocator;
import com.gamefactory.utils.events.Event;
import com.gamefactory.utils.events.Observer;
import java.util.EventObject;

public class Sound extends Component {

    private static AudioEngine ae;

    private final static Observer o = (Event event) -> {
            if (((Event) event).getEvent().equals("AUDIO_SERVICE_PROVIDED")) {
                ae = (AudioEngine) ServiceLocator.getService("AUDIO");
                //o est conservé en mémoire car il est toujours référencé alors qu'il est inutile :/
            }
        };
    
    static {
        ServiceLocator.getNotifier().registerObserver(o);
    }

    @Override
    public void init(ComponentManager owner) {
        super.init(owner);
    }
    
    public AudioEngine getAudioEngine() {
        return ae;
    }

    @Override
    public void update() {
    }
}
