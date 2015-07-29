/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable.scripts;

import com.gamefactory.displayable.Script;

public abstract class LoadingScript extends Script {

    private boolean isConsumed = false;

    public abstract void executeOnce();

    @Override
    public boolean isConsumed() {
        return this.isConsumed;
    }

}
