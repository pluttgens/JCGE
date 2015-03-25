package com.gamefactory.game;

import com.gamefactory.callbacks.game.Callbacks;
import java.awt.Graphics;

/**
 * Interface définissant les deux méthodes à implémenter pour qu'un objet soit
 * affichable par le jeu.
 *
 * @author Pascal Luttgens
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface Displayable<T extends Displayable> extends Callbacks<T> {
    
    void update();

    void render(Graphics g);

}
