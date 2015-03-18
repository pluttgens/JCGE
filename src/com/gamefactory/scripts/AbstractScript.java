/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;
import com.gamefactory.displayable.Script;
import com.gamefactory.displayable.ScriptManager;
/**
 *
 * @author rakotoar
 */
public abstract class AbstractScript implements Script{
   protected ScriptManager owner;
   
    @Override
    public void init(ScriptManager owner) {
        this.owner = owner;
        
    }

   

    
    
}
