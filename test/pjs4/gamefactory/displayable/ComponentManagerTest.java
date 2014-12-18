/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjs4.gamefactory.displayable;

import static org.junit.Assert.*;
import org.junit.Test;
import pjs4.gamefactory.components.Position;
import pjs4.gamefactory.components.RigidBody;

/**
 *
 * @author scalpa
 */
public class ComponentManagerTest {

    @Test
    public void test() {
        ComponentManager cm = new ComponentManager();
        String[] components = {"Position", "RigidBody"};
        cm.init(components);
        assertTrue("Le component 'Position' a bien été ajouté ! ( STRING_CHECK )", cm.checkForComponent("Position"));
        assertTrue("Le component 'Position' a bien été ajouté ! ( CLASS_CHECK )", cm.checkForComponent(Position.class));
        assertTrue("Le component 'RigidBody' a bien été ajouté ! ( STRING_CHECK )", cm.checkForComponent("RigidBody"));
        assertTrue("Le component 'RigidBody' a bien été ajouté ! ( CLASS_CHECK )", cm.checkForComponent(RigidBody.class));

    }

}
