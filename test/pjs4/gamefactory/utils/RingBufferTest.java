/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.utils;

import pjs4.gamefactory.utils.collections.RingBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author scalpa
 */
public class RingBufferTest {

    @Test
    public void test() {
        RingBuffer<String> rb = new RingBuffer<>(3);

        rb.add("1");
        rb.add("2");
        assertEquals("1", rb.get());
        assertEquals("2", rb.get());
        assertTrue(rb.isEmpty());

        rb.add("1");
        rb.add("2");
        rb.add("3");
        rb.add("4");
        assertEquals("1", rb.get());
        assertEquals("2", rb.get());
        assertEquals("3", rb.get());
        assertEquals("4", rb.get());
        assertTrue(rb.isEmpty());
    }

}
