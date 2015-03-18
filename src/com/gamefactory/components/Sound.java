package com.gamefactory.components;

import com.gamefactory.audioengine.AudioEngine;
import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.services.ServiceLocator;
import com.gamefactory.utils.events.Event;
import com.gamefactory.utils.events.Observer;

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

    public AudioEngine getAudioEngine() {
        return ae;
    }

}
