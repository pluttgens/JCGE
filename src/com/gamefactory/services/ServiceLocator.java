package com.gamefactory.services;

import com.gamefactory.assets.assetmanager.AssetManager;
import com.gamefactory.utils.events.Notifier;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Le locateur de service sert d'interface entre le code et les services afin de
 * les découpler et de contrôler l'instance des services.
 *
 * Il possède aussi des méthodes statiques utilites notamment pour l'utilisation
 * des objet JSON.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ServiceLocator {

    private final static Notifier notifier = new Notifier(new Object());
    private final static JSONObject config = getJSONObject(new File("config/config.cfg"));
    private final static HashMap<String, Service> services = new HashMap<>();
    private static AssetManager assetManager;
    private static GameWindow gameWindow;

    public static void provideService(String serviceName, Service service) {
        ServiceLocator.services.put(serviceName.toUpperCase(), service);
        ServiceLocator.notifier.notifyObservers(serviceName.toUpperCase() + "_SERVICE_PROVIDED");
    }

    public static void provideAssetManager(AssetManager assetManager) {
        ServiceLocator.assetManager = assetManager;
        ServiceLocator.notifier.notifyObservers("ASSET_MANAGER_PROVIDED");
    }

    public static Service getService(String type) {
        return ServiceLocator.services.get(type);
    }

    public static AssetManager getAssetManager() {
        return assetManager;
    }

    public static JSONObject getConfig() {
        return ServiceLocator.config;
    }

    public static void provideGameWindow(JFrame frame, Canvas canvas) {
        ServiceLocator.gameWindow = new GameWindow(canvas, frame);
        ServiceLocator.notifier.notifyObservers("GAME_PROVIDED");
    }

    public static GameWindow getGameWindow() {
        return ServiceLocator.gameWindow;
    }

    public static JSONObject getJSONObject(File file) {
        try (FileReader fr = new FileReader(file)) {
            String json = new String();
            while (fr.ready()) {
                json += (char) fr.read();
            }
            int index = json.indexOf("{");
            json = json.substring(index);
            return new JSONObject(json);
        } catch (IOException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public static JSONObject getJSONObject(String json) {
        int index = json.indexOf("{");
        json = json.substring(index);
        return new JSONObject(json);

    }

    public static Notifier getNotifier() {
        return ServiceLocator.notifier;
    }

    public static class GameWindow {

        private final Canvas canvas;
        private final JFrame frame;

        public GameWindow(Canvas canvas, JFrame frame) {
            this.canvas = canvas;
            this.frame = frame;
        }

        public Canvas getCanvas() {
            return canvas;
        }

        public JFrame getFrame() {
            return frame;
        }

    }
}
