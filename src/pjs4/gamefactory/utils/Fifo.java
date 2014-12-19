
package pjs4.gamefactory.utils;

/**
 *
 * @author scalpa
 * @param <E>
 */
public interface Fifo<E> {

    void add(E e);

    E get();

}
