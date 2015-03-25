/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.components;

import com.gamefactory.displayable.Component;

/**
 *
 * @author scalpa
 */
public class Health extends Component {

    private int hp;

    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public void add(int hp) {
        this.hp += hp;
    }
    
    public void sub(int hp) {
        this.hp -= hp;
    }
    
    private boolean isAlive() {
        return this.hp != 0;
    }
    
}
