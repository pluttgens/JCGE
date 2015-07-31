package com.gg.jcge.game;

import com.gg.jcge.assets.assetmanager.AssetManager;
import com.gg.jcge.audioengine.AudioEngine;
import com.gg.jcge.components.Sound;
import com.gg.jcge.displayable.Scene;
import com.gg.jcge.displayable.SceneManager;
import com.gg.jcge.displayable.gameobjects.EmptyGameObject;
import com.gg.jcge.services.ServiceLocator;
import org.json.JSONObject;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

/**
 *
 * @author Pascal Luttgens
 */
public class Game extends Canvas implements Runnable {

    public final static int WINDOW_BORDER_SIZE = 20;
    private final static String CONFIG_KEY = Game.class.getSimpleName().toLowerCase();
    private final static JSONObject config = ServiceLocator.getConfig().getJSONObject(CONFIG_KEY);
    /**
     * Longueur de la fenêtre.
     */
    public final static int WIDTH = config.getInt("width");

    /**
     * Hauteur de la fenêtre.
     */
    public final static int HEIGHT = config.getInt("height");

    /**
     * Nom du jeu.
     */
    private final static String NAME = config.getString("name");

    /**
     * Thread du jeu.
     */
    private Thread thread;

    /**
     * Flag indiquant si le thread est utilisé, apparament inutile, à supprimer.
     */
    private boolean running;

    /**
     * Ensemble des scènes du jeu.
     *
     * @see Scene
     * @see Displayable
     */
    private Displayable displayable;

    /**
     * Construit un jeu.
     *
     * @see #window()
     */
    public Game() {
        window();
    }

    public static void main(String[] args) throws IOException {

        Sound.init();

        ServiceLocator.provideAssetManager(new AssetManager());
        Game game = new Game();

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

    /**
     * Crée une fenêtre pour le jeu en fonction de ses dimensions et de son nom.
     *
     * @see java.awt.Window
     */
    private void window() {
        new Window(WIDTH, HEIGHT, NAME, this);
    }

    public void setDisplayable(Displayable displayable) {
        this.displayable = displayable;
        displayable.load();
    }

    public void init() {
        ServiceLocator.provideService("audio", new AudioEngine());
        SceneManager sceneManager = new SceneManager() {

            @Override
            protected void init() {
                this.add(new Scene() {
                    @Override
                    protected void init() {

                    }
                });
            }

        };
        sceneManager.init(new EmptyGameObject());
        sceneManager.load();
        this.displayable = sceneManager;
    }

    /**
     * Démarre le processus du jeu.
     */
    public synchronized void start() {
        if (!running) {
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

    public void loadGame() {
        displayable.load();
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
}
