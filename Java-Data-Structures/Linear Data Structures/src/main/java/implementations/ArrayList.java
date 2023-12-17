package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 4;
    private Object[] elements;
    private int capacity;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        checkForGrow();

        this.elements[this.size++] = element;

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (checkIndexValidations(index)) return false;

        insertElements(index, element);

        size++;

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (checkIndexValidations(index)) throw new IndexOutOfBoundsException();

        return (E) this.elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (checkIndexValidations(index)) throw new IndexOutOfBoundsException();

        E prev = (E) this.elements[index];

        this.elements[index] = element;

        return prev;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (checkIndexValidations(index)) throw new IndexOutOfBoundsException();

        E element = (E) this.elements[index];

        this.size--;

        shift(index);
        ensureCapacity();

        return element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void checkForGrow() {
        if (this.size == this.capacity) {
            this.elements = grow();
        }
    }

    private Object[] grow() {
        this.capacity *= 2;
        return Arrays.copyOf(this.elements, this.elements.length * 2);
    }

    private boolean checkIndexValidations(int index) {
        return index < 0 || index >= this.size;
    }

    private void insertElements(int index, E element) {
        checkForGrow();

        for (int i = this.size - 1 ; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }

        this.elements[index] = element;
    }

    private void shift(int index) {
        for (int i = index; i < capacity - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private Object[] shrink() {
        this.capacity /= 3;

        return Arrays.copyOf(this.elements, this.elements.length / 3);
    }

    private void ensureCapacity() {
        if (this.size < this.capacity / 3) {
            this.elements = shrink();
        }
    }

}
