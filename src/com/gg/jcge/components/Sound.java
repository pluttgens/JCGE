package com.gg.jcge.components;

import com.gg.jcge.audioengine.AudioEngine;
import com.gg.jcge.displayable.Component;
import com.gg.jcge.services.ServiceLocator;
import com.gg.jcge.utils.events.Event;
import com.gg.jcge.utils.events.Observer;

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
