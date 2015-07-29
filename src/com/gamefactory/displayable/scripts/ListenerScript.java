/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable.scripts;

import com.gamefactory.displayable.Script;
import com.gamefactory.utils.events.Event;

/**
 *
 * @author scalpa
 */
public abstract class ListenerScript extends Script {

    public abstract void onEvent(Event e);

    @Override
    public final boolean isConsumed() {
        return false;
    }

}
