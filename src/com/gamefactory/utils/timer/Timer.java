package com.gamefactory.utils.timer;

import java.util.concurrent.TimeUnit;

public class Timer {
    
    private static final long GLOBAL_START_TIME = System.nanoTime();
    
    private long startTime;
    
    public void start() {
        this.startTime = System.nanoTime();
    }
    
    public static long getGlobalElapsedTime(TimeUnit tu) {
        return TimeUnit.NANOSECONDS.convert(System.nanoTime() -  Timer.GLOBAL_START_TIME, tu);
    }
    
    public long getElapsedTime(TimeUnit tu) {
        return tu.convert(System.nanoTime() - this.startTime, TimeUnit.NANOSECONDS);
    }
    
    public void resetTimer() {
        this.startTime = System.nanoTime();
    }
    
}
