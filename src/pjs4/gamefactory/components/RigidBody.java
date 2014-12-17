package pjs4.gamefactory.components;

import pjs4.gamefactory.displayable.Component;

/**
 *
 * @author scalpa
 */
public class RigidBody extends Component {
    
    private float gravity;
    
    public RigidBody() {
        this.gravity = 0;
    }
    
    public float getGravity() {
        return this.gravity;
    }
    
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
 
    public void update() {
        
    }
    
    
}
