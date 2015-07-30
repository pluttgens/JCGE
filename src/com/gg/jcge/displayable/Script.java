/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.displayable;

/**
 *
 * @author rakotoar
 */
public abstract class Script {

    protected ScriptManager scriptManager;

    public void init(ScriptManager scriptManager) {
        this.scriptManager = scriptManager;
    }

    public void load() {

    }

    ;

    public abstract boolean isConsumed();
    
    
}
