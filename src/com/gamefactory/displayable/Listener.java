/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.callbacks.game.Callbacks;

/**
 *
 * @author scalpa
 */
public interface Listener<T, U> extends Callbacks<T> {
    
    void onEvent(U u);
    
}
