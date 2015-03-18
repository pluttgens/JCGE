/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.Script;

/**
 *
 * @author scalpa
 */
public abstract class UpdateScript<T> implements Script<T> {
    
    protected T owner;

    @Override
    public void init(T t) {
        this.owner = t;
    }
    
    @Override
    public final boolean isConsumed() {
        return false;
    }
}
