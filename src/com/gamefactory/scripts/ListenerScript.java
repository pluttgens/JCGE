/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.game.Displayable;

/**
 *
 * @author scalpa
 */
public abstract class ListenerScript<T extends Displayable> extends AbstractScript<T> {

    @Override
    public final void execute() {
        onEvent();
    }

    public abstract void onEvent();

    @Override
    public final boolean isConsumed() {
        return false;
    }

}
