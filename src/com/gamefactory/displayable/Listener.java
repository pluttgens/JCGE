/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

/**
 *
 * @author scalpa
 */
public interface Listener<T, U> {
    
    void init(T t);
    
    void onEvent(U u);
    
}
