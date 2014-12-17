/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.displayable;

/**
 *
 * @author scalpa
 */
public class NoSuchComponentException extends RuntimeException {

    public NoSuchComponentException(String string) {
        super(string);
    }

    public NoSuchComponentException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public NoSuchComponentException(Throwable thrwbl) {
        super(thrwbl);
    }

    public NoSuchComponentException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
