package pjs4.gamefactory.displayable;

import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
/**
 *
 * @author scalpa
 */
public class GameObjectTest {

    @Test
    public void test() {
        GameObject go = new GameObject() {

            @Override
            public void init() {
                String[] components = {"Position", "RigidBody"};
                componentManager.init(components);
            }
        };
        // TODO mais pour l'instant y'a rien a tester.
    }
}
