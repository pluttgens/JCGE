/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.callbacks;

import java.util.function.Consumer;

public interface Loadable {

    void load();

    public class ConsumerImpl implements Consumer<Loadable> {

        @Override
        public void accept(Loadable l) {
            l.load();
        }
    }
}
