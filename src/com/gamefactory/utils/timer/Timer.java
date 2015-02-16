/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.utils.timer;

/**
 *
 * @author scalpa
 */
public class Timer {
    
    private long startTime;

    public Timer() {
        this.startTime = System.nanoTime();
    }
    
    public long getElapsedTime() {
        return System.nanoTime() - this.startTime;
    }
    
    public void resetTimer() {
        this.startTime = System.nanoTime();
    }
    
}
