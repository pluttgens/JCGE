/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.displayable;

import static org.junit.Assert.*;
import org.junit.Test;
import com.gamefactory.components.Position;
import com.gamefactory.components.RigidBody;
import com.gamefactory.displayable.gameobjects.EmptyGameObject;

/**
 *
 * @author Pascal Luttgens
 */
public class ComponentManagerTest {

    @Test
    public void test() {
        ComponentManager cm = new ComponentManager();
        cm.init(new EmptyGameObject());
        cm.add(new Position(), new RigidBody());
        assertTrue("Le component 'Position' a bien été ajouté ! ( STRING_CHECK )", cm.checkForComponent("Position"));
        assertTrue("Le component 'Position' a bien été ajouté ! ( CLASS_CHECK )", cm.checkForComponent(Position.class));
        assertTrue("Le component 'RigidBody' a bien été ajouté ! ( STRING_CHECK )", cm.checkForComponent("RigidBody"));
        assertTrue("Le component 'RigidBody' a bien été ajouté ! ( CLASS_CHECK )", cm.checkForComponent(RigidBody.class));
        cm.load();
    }

}
