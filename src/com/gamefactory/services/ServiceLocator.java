package com.gamefactory.services;

import com.gamefactory.assets.assetmanager.AssetManager;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

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

    private final static JSONObject config = getJSONObjectFromFile(new File("config/config.cfg"));
    private final static HashMap<String, Service> services = new HashMap<>();
    private static AssetManager assetManager;

    public static void provideService(String serviceName, Service service) {
        ServiceLocator.services.put(serviceName, service);
    }

    public static void provideAssetManager(AssetManager assetManager) {
        ServiceLocator.assetManager = assetManager;
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

    public static JSONObject getJSONObjectFromFile(File file) {
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
}
