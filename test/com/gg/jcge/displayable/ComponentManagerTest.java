/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.displayable;

import com.gg.jcge.components.Position;
import com.gg.jcge.components.RigidBody;
import com.gg.jcge.displayable.gameobjects.EmptyGameObject;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
