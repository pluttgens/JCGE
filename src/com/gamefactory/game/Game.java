package com.gamefactory.game;

import com.gamefactory.assets.assetmanager.AssetManager;
import com.gamefactory.audioengine.AudioEngine;
import com.gamefactory.displayable.gameobjects.EmptyGameObject;
import com.gamefactory.displayable.gameobjects.Hero;
import com.gamefactory.displayable.Scene;
import com.gamefactory.services.ServiceLocator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @autimport java.io.InputStream; hor Pascal Luttgens
 */
public class Game extends Canvas implements Runnable {

    /**
     * Longueur de la fenetre.
     */
    public final static int WIDTH = 400;

    /**
     * Hauteur de la fenetre.
     */
    public final static int HEIGHT = 600;

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
     * @see pcomgamefactory.displayable.Scene
     * @see Displayable
     */
    private Displayable displayable;

    /**
     * Construit un jeu à partir des dimensions de sa fenetre et de son nom.
     *
     * @param WIDTH  La longueur de la fenetre
     * @param HEIGHT La hauteur de la fenetre
     * @param NAME   Le nom du jeu
     *
     * @see #window()
     */
    public Game(int WIDTH, int HEIGHT, String NAME) {
        this.NAME = NAME;
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

    public void setDisplayable(Displayable displayable) {
        this.displayable = displayable;
        displayable.init();
    }

    public void init() {
        ServiceLocator.provideService("audio", new AudioEngine());
        this.displayable = new Scene();
        this.displayable.init();
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
        displayable.update();
    }

    /**
     * Met à jour l'affichage de tous les élément de la scene
     */
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        displayable.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) throws IOException {
        try {
            Class.forName("com.gamefactory.components.Sound");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServiceLocator.provideAssetManager(new AssetManager());
        Game game = new Game(800, 600, "test");

        /*
         * try {
         *
         * AudioEngine ae = new AudioEngine(); ae.start(); Notifier n = new
         * Notifier(); n.registerObserver(ae); n.notifyObservers(new
         * AudioEvent(n, AudioEvent.Type.PLAY, "test1.wav"));
         * n.notifyObservers(new AudioEvent(n, AudioEvent.Type.PLAY,
         * "test2.wav")); Thread.sleep(500); n.notifyObservers(new AudioEvent(n,
         * AudioEvent.Type.PLAY, "test1.wav")); Thread.sleep(10000);
         * n.notifyObservers(new AudioEvent(n, AudioEvent.Type.PLAY,
         * "test2.wav", "2")); n.notifyObservers(new AudioEvent(n,
         * AudioEvent.Type.PLAY, "test1.wav")); n.notifyObservers(new
         * AudioEvent(n, AudioEvent.Type.STOP, "test2.wav"));
         * Thread.sleep(10000); n.notifyObservers(new AudioEvent(n,
         * AudioEvent.Type.PLAY, "test2.wav")); } catch (InterruptedException
         * ex) { Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null,
         * ex); }
         */
    }
}
