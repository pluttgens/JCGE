package pjs4.gamefactory.displayable;

import java.util.LinkedList;
import pjs4.gamefactory.components.Position;
import pjs4.gamefactory.game.Displayable;

/**
 *
 * @author scalpa
 */
public abstract class GameObject implements Displayable {

    protected final ComponentManager componentManager;
    private boolean isActive;

    public GameObject() {
        this.componentManager = new ComponentManager();
        this.isActive = false;
    }

    public abstract void innit();

    @Override
    public void update() {
    }

    @Override
    public void render() {
    }
}
