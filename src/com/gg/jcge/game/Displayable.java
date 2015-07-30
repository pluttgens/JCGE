package com.gg.jcge.game;

import java.awt.*;

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
public interface Displayable<T extends Displayable> {

    void init(T owner);

    void load();

    void update();

    void render(Graphics g);

}
