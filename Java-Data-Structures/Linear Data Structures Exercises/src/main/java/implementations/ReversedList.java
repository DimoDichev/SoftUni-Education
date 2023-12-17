package implementations;

import java.util.Iterator;

public class ReversedList<E> implements Iterable<E> {

    private final int DEFAULT_CAPACITY = 2;
    private int head;
    private int size;
    private Object[] elements;

    public ReversedList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.head = DEFAULT_CAPACITY - 1;
        this.size = 0;
    }

    public void add(E element) {
        if (this.head < 0) grow();
        this.elements[this.head--] = element;
        this.size++;
    }

    private void grow() {
        Object[] newElements = new Object[elements.length * 2];
        int newElementsIndex = newElements.length - 1;
        for (int i = this.elements.length - 1; i >= 0 ; i--) {
            newElements[newElementsIndex--] = this.elements[i];
        }
        this.elements = newElements;
        this.head = this.elements.length - this.size - 1;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.elements.length;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) return null;
        return (E) this.elements[this.head + index + 1];
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size) return;
        int realIndex = this.head + index + 1;

        for (int i = realIndex ; i >= this.head ; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[this.head++] = null;
        size--;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = head + 1;
            @Override
            public boolean hasNext() {
                return index < elements.length;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                return (E) elements[index++];
            }
        };
    }
}
