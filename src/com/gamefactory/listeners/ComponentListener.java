/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.listeners;

import com.gamefactory.displayable.Component;
import com.gamefactory.displayable.Listener;

/**
 *
 * @author scalpa
 */
public abstract class ComponentListener<T extends Component, U> implements Listener<T, U> {

    protected T owner;

    @Override
    public void init(T t) {
        this.owner = t;
    }

}
