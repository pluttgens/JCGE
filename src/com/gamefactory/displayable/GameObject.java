package com.gamefactory.displayable;

import com.gamefactory.game.Displayable;
import java.awt.Graphics;

/**
 * Superclass représentant tous les objets du jeu.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public abstract class GameObject implements Displayable {

    /**
     * Encapsulation de l'ensemble des components du Game Object
     *
     * - Pascal Luttgens.
     */
    protected final ComponentManager componentManager;

    /**
     * Flag indiquant si le Game Object doit call update et render. Utile pour
     * le pattern object pool qui consiste a avoir une liste d'objets
     * réutilisables afin d'éviter les opérations d'allocation de mémoire.
     *
     * - Pascal Luttgens.
     */
    private boolean isActive;

    public GameObject(Class<? extends Component> c) {
        this.componentManager = new ComponentManager();
        this.isActive = false;
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
    public abstract void init();

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
     */
    protected void renderObject(Graphics g) {

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
}
