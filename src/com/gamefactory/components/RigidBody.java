package com.gamefactory.components;

import com.gamefactory.displayable.Component;

/**
 * Component permettant de gérer les effets de la gravité sur l'objet.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class RigidBody extends Component {

    private float gravity = 0;


    /**
     * Recupere la gravite du personnage
     * @return
     */
    public float getGravity() {
        return this.gravity;
    }

    /**
     * Initialise la gravite du personnage
     * @param gravity
     */
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

}
