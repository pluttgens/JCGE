package pjs4.gamefactory.game;

import java.awt.Canvas;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjs4.gamefactory.audioengine.AudioEngine;
import pjs4.gamefactory.displayable.Scene;

/**
 *
 * @author Pascal Luttgens
 */
public class Game extends Canvas implements Runnable {

    /**
     * Longueur de la fenetre.
     */
    private int WIDTH;

    /**
     * Hauteur de la fenetre.
     */
    private int HEIGHT;

    /**
     * Nom du jeu.
     */
    private String NAME;

    /**
     * Thread du jeu.
     */
    private Thread thread;

    /**
     * Flag indiquant si le thread est utlisé, apparament inutile, à supprimer.
     */
    private boolean running;

    /**
     * Ensemble des scene du jeu.
     *
     * @see pjs4.gamefactory.displayable.Scene
     * @see Displayable
     */
    private Displayable scene;

    /**
     * Construit un jeu à partir des dimensions de sa fenetre et de son nom.
     *
     * @param WIDTH  La longueur de la fenetre
     * @param HEIGHT La hauteur de la fenetre
     * @param NAME   Le nom du jeu
     *
     * @see #window()
     */
    public Game(int WIDTH, int HEIGHT, String NAME, Displayable scene) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.NAME = NAME;
        this.scene = scene;
        window();
    }

    /**
     * Crée une fenetre pour le jeu en fonction de ses dimension et de son nom.
     *
     * @see Window
     */
    private void window() {
        new Window(this.WIDTH, this.HEIGHT, this.NAME, this);
    }

    /**
     * Demarre le processus du jeu.
     */
    public synchronized void start() {
        if (running == false) {
            thread = new Thread(this);
            thread.start();
            running = true;
        }
    }

    /**
     * Termine le processus du jeu.
     */
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace(); //affiche l'état de la pile d'éxécution lors de l'erreur
        }
    }

    /**
     * Boucle du jeu, mets à jour les éléments de façon constante et les
     * affiches correctement à l'écran.
     *
     */
    @Override
    public void run() {
        //Perso je comprends pas entièrement comment ça marche, mais ça marche.
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    /**
     * Met à jour tous les élément de la scene
     */
    public void update() {
        scene.update();
    }

    /**
     * Met à jour l'affichage de tous les élément de la scene
     */
    public void render() {
        scene.render();
    }

    public static void main(String[] args) {
        new Game(800, 600, "test", new Scene());
    }
}
