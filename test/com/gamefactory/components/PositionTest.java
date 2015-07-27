package com.gamefactory.components;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class PositionTest {
    
    public PositionTest() {
    }

    @Test
    public void test() {
        final double delta = 0.001;
        
        final Position p1 = new Position();
        final Position p2 = new Position();
        
        p1.setX(10);
        p1.setY(5);
        
        assertEquals(10, p1.getX(), delta);
        assertEquals(5, p1.getY(), delta);
        
        p1.setHeight(64);
        p1.setWidth(32);
        
        assertEquals(64, p1.getHeight(), delta);
        assertEquals(32, p1.getWidth(), delta);
        
        assertEquals(Position.Orientation.DOWN, p1.getOrientation());
        
        p1.setOrientation(Position.Orientation.UP);
        
        assertEquals(Position.Orientation.UP, p1.getOrientation());
        
        p2.setX(20);
        p2.setY(5);
        
        assertEquals(10, p1.distanceWith(p2), delta);
        assertEquals(10, p2.distanceWith(p1), delta);
        
        final Position p3 = p1.deepClone();
        
        assertEquals(p1, p3);
        assertNotSame(p1, p3);
        
    }
    
}
