/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.displayable.GameObject;

/**
 *
 * @author scalpa
 */
public interface OnCollisionListener extends ComponentListener{
    
    void onEnterCollision(GameObject go);
}
