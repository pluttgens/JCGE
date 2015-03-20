package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import com.gamefactory.utils.events.Notifier;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Superclass représentant tous les objets du jeu.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public abstract class GameObject implements Displayable<Scene> {

    protected final Notifier notifier;

    /**
     * Encapsulation de l'ensemble des components du Game Object
     *
     * - Pascal Luttgens.
     */
    protected final ComponentManager componentManager;

    protected final String id;

    /**
     * Flag indiquant si le Game Object doit call update et render. Utile pour
     * le pattern object pool qui consiste a avoir une liste d'objets
     * réutilisables afin d'éviter les opérations d'allocation de mémoire.
     *
     * - Pascal Luttgens.
     */
    private boolean isActive;

    protected Scene owner;

    public GameObject() {
        this.componentManager = new ComponentManager();
        this.isActive = true;
        this.id = this.getClass().getSimpleName().toUpperCase();;
        this.notifier = new Notifier(this);
    }

    protected GameObject(String id) {
        this.componentManager = new ComponentManager();
        this.isActive = true;
        this.id = id.toUpperCase();
        this.notifier = new Notifier(this);
    }

    /**
     * Doit être overridée pour spécifier la liste des components à charger dans
     * le component manager. Attention : Les components doivent se trouver dans
     * le package components. Pour éviter les pertes de performances, il faut
     * ordonner les components en fonction des call de la fonction update. Un
     * contrôle est quand même effectué lors de l'initialisation du CM.
     *
     * - Pascal Luttgens.
     */
    /**
     * Recupere l'id du GameObject
     *
     * @return
     */
    public String getId() {
        return this.id;
    }

    /**
     * Initialise la scene
     *
     * @param scene
     */
    public void setOwner(Scene scene) {
        this.owner = scene;
    }

    /**
     * Recupere la scene
     *
     * @return
     */
    public Scene getOwner() {
        return this.owner;
    }

    /**
     * Recupere le ComponentManager encapsulant le comportement des components
     * au sein d'un GameObject
     *
     * @return
     */
    public ComponentManager getComponentManager() {
        return this.componentManager;
    }

    @Override
    public void init(Scene owner) {
        this.owner = owner;
        this.componentManager.init(this);
    }

    @Override
    public void load() {
        
    }
    
    
    /**
     * Vérifie que le GameObject est actif avant de procéder à l'update.
     *
     * - Pascal Luttgens.
     */
    @Override
    public final void update() {
        if (this.isActive) {
            updateObject();
        }
    }

    /**
     * Méthode à Override pour définir le comportement du Game Object lors d'un
     * call de son cycle d'update.
     *
     * - Pascal Luttgens.
     */
    protected void updateObject() {
        componentManager.update();
    }

    /**
     * Vérifie que le Game Object est actif avant de procéder au rendering.
     *
     * - Pascal Luttgens.
     *
     * @param g
     */
    @Override
    public final void render(Graphics g) {
        if (this.isActive) {
            renderObject(g);
        }
    }

    /**
     * Méthode à Override pour définir le comportement du Game Object lors d'un
     * call de son rendering.
     *
     * - Pascal Luttgens.
     *
     * @param g
     */
    protected void renderObject(Graphics g) {
        componentManager.render(g);
    }

    /**
     * Active l'update et le rendering du Game Object.
     *
     * - Pascal Luttgens.
     */
    public void active() {
        this.isActive = true;
    }

    /**
     * Désactive l'update et le rendering du Game Object.
     *
     * - Pascal Luttgens.
     */
    public void disable() {
        this.isActive = false;
    }

    public boolean isInCameraField() {
        return true;
    }

}
