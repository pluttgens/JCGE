/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.displayable.Manager;

public abstract class LoadingScript<T extends Manager> extends AbstractScript<T> {

    private boolean isConsumed = false;


    @Override
    public final void execute() {
        isConsumed = true;
        executeOnce();
    }

    protected abstract void executeOnce();

    @Override
    public boolean isConsumed() {
        return this.isConsumed;
    }

}
