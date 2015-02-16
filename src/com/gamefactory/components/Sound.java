package com.gamefactory.components;

import com.gamefactory.audioengine.AudioEngine;
import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.services.ServiceLocator;
import com.gamefactory.utils.events.Event;
import com.gamefactory.utils.events.Observer;

public class Sound extends Component {

    private static AudioEngine ae;

    static {
        Observer o = (Event event) -> {
            if (event.getEvent().equals("AUDIO_SERVICE_PROVIDED")) {
                ae = (AudioEngine) ServiceLocator.getService("AUDIO");
                //o est conservé en mémoire car il est toujours référencé alors qu'il est inutile :/
            }
        };
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
