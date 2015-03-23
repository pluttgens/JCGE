/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.game.Displayable;
import com.gamefactory.utils.events.Event;

/**
 *
 * @author scalpa
 */
public abstract class ListenerScript<T extends Displayable> extends AbstractScript<T> {

    public abstract void onEvent(Event e);

    @Override
    public final boolean isConsumed() {
        return false;
    }

}
