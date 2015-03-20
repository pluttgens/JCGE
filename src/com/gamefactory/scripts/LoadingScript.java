/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.game.Displayable;

public abstract class LoadingScript<T extends Displayable> extends AbstractScript<T> {

    private boolean isConsumed = false;

    public abstract void executeOnce();

    @Override
    public boolean isConsumed() {
        return this.isConsumed;
    }

}
