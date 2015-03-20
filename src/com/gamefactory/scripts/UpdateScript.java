/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.displayable.Manager;

/**
 *
 * @param <T>
 */
public abstract class UpdateScript<T extends Manager>  extends AbstractScript<T> {
    
    
    
    @Override
    public final boolean isConsumed() {
        return false;
    }
}
