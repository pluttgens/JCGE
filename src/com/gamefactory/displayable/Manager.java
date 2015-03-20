/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import java.awt.Graphics;

/**
 *
 * @author scalpa
 */
public interface Manager<T extends Displayable, U> extends Displayable<T> {
    
    GameObject getGameObject(String id);

    void add(U ... u);

}
