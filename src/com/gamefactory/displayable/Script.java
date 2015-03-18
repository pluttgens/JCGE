/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import com.gamefactory.callbacks.game.Callbacks;

public interface Script extends Callbacks<ScriptManager> {
    
    void execute();
    
    boolean isConsumed();
}
