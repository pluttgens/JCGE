package pjs4.gamefactory.utils;

/**
 * Implémentation d'une FIFO en RingBuffer. C'est un simple tableau circulaire
 * qui permet d'éviter de décaler tous les éléments de la FIFO à chaque call sur
 * get.
 *
 *
 * @author Pascal Luttgens
 * @param <E>
 */
public class RingBuffer<E> extends AbstractFifo<E> {

    protected final Object lock;
    
    protected Object[] collection_;
    protected int head_;
    protected int tail_;
    protected int count_;

    /**
     * Construit une FIFO avec une capacité initiale donnée
     *
     * - Pascal Luttgens.
     *
     * @param size La capacité initiale de la FIFO.
     */
    public RingBuffer(int size) {
        this.SIZE_ = size;
        this.collection_ = new Object[size];
        this.count_ = 0;
        this.lock = new Object();
    }

    /**
     * Construit une FIFO avec la capacité initiale de 15 éléments.
     *
     * - Pascal Luttgens.
     */
    public RingBuffer() {
        this(15);
    }

    /**
     * Ajoute un élément à la FIFO.
     *
     * - Pascal Luttgens.
     *
     * @param e L'élément à ajouter.
     */
    @Override
    public void add(E e) {
        synchronized (this.lock) {
            ensureCapacity();
            this.collection_[this.tail_] = e;
            this.tail_ = (this.tail_ + 1) % this.SIZE_;
            ++this.count_;
            lock.notifyAll();
        }
    }

    /**
     * Retourne le dernier élément ajouté selon le principe FIFO.
     *
     * - Pascal Luttgens.
     *
     * @return Le dernier élément ajouté.
     */
    @Override
    public E get() {
        synchronized (this.lock) {
            while (isEmpty()) {
                try {
                    this.lock.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            E ret = (E) this.collection_[this.head_];
            this.head_ = (this.head_ + 1) % this.SIZE_;
            --this.count_;
            return ret;
        }
    }

    /**
     * Vérifie que la capacité de la FIFO est suffisante pour accueillir un
     * nouvel élément.
     *
     * - Pascal Luttgens.
     */
    protected void ensureCapacity() {
        synchronized (this.lock) {
            if ((!isEmpty()) && (this.head_ - (this.tail_ % this.SIZE_ ) == 0)) {
                Object[] temp = new Object[this.SIZE_ * 2];
                int indTemp = 0;
                for (int i = this.head_; i < this.SIZE_; ++i) {
                    temp[indTemp++] = this.collection_[i];
                }
                for (int i = 0; i < this.head_; ++i) {
                    temp[indTemp++] = this.collection_[i];
                }
                this.collection_ = temp;
                this.head_ = 0;
                this.tail_ = SIZE_;
                this.SIZE_ = SIZE_ * 2;
            }
        }
    }

    /**
     * Indique si la FIFO est vide.
     *
     * - Pascal Luttgens.
     *
     * @return True si la FIFO est vide.
     */
    public boolean isEmpty() {
        synchronized (this.lock) {
            return (this.count_ == 0);
        }
    }
}
