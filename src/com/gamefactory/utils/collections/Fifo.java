
package com.gamefactory.utils.collections;

/**
 *
 * @author Pascal Luttgens
 * @param <E>
 */
public interface Fifo<E> {

    void add(E e);

    E get();

}
