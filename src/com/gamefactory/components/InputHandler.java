package com.gamefactory.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gamefactory.displayable.Component;
import com.gamefactory.utils.events.Notifier;
import com.gamefactory.utils.events.Subject;

/**
 * 
 * 
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class InputHandler  extends Component implements KeyListener{
	private Position p;

    //private final Notifier notifier;

    public InputHandler() {
    }
    
    public void init(Position p1){
    	p=p1;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    	int key = ke.getKeyCode();
    	
        if(key == KeyEvent.VK_LEFT){
     	   p.setxVelocity(-1f);
        }
        if(key == KeyEvent.VK_RIGHT){
     	   p.setxVelocity(1f);
        }
        if(key == KeyEvent.VK_UP){
     	   p.setyVelocity(1f);
        }
        if(key == KeyEvent.VK_DOWN){
     	   p.setyVelocity(-1f);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	p.setxVelocity(0f);
        }

        if (key == KeyEvent.VK_RIGHT) {
        	p.setxVelocity(0f);
        }

        if (key == KeyEvent.VK_UP) {
        	p.setyVelocity(0f);
        }

        if (key == KeyEvent.VK_DOWN) {
        	p.setyVelocity(0f);
        }
    }

   /* @Override
    public Notifier getNotifier() {
        return this.notifier;
    }*/

}
