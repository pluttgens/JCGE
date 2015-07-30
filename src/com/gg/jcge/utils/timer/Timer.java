package com.gg.jcge.utils.timer;

import java.util.concurrent.TimeUnit;

public class Timer {
    
    private static final long GLOBAL_START_TIME = System.nanoTime();
    
    private long startTime;
    
    public static long getGlobalElapsedTime(TimeUnit tu) {
        return TimeUnit.NANOSECONDS.convert(System.nanoTime() -  Timer.GLOBAL_START_TIME, tu);
    }

    public void start() {
        this.startTime = System.nanoTime();
    }
    
    public long getElapsedTime(TimeUnit tu) {
        return tu.convert(System.nanoTime() - this.startTime, TimeUnit.NANOSECONDS);
    }
    
    public void resetTimer() {
        this.startTime = System.nanoTime();
    }
    
}
