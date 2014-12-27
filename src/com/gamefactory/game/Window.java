package com.gamefactory.game;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Fenetre du jeu.
 * 
 * @author Pascal Luttgens
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public class Window extends Canvas {
    
    public Window(int width, int heigth, String title, Game game) {
        
        // On crée une nouvelle fenetre en lui passant un titre
        JFrame frame = new JFrame(title);
        
        //On définit sa taille
        frame.setPreferredSize(new Dimension(width, heigth));
        frame.setMinimumSize(new Dimension(width, heigth));
        frame.setMaximumSize(new Dimension(width, heigth));
        
        //Permet de rendre les boutons en haut a droite fonctionnels
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Permet d'empecher le redimensionnement
        frame.setResizable(false);
        //Place la fenetre au milieu de l'ecran
        frame.setLocationRelativeTo(null);
        //Ajoute le jeu au processus de la fenetre
        frame.add(game);
        //Montre la fenetre
        frame.setVisible(true);
        //Lance le jeu
        game.start();
        
    }
}
