/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.utils.builders;

/**
 *
 * @author scalpa
 */
public class ArrayBuilder {
    public static final <T> T[] asArray(T ... t) {
        T[] ret = t;
        return ret;
    }
}
