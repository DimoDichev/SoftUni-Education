package implementations;

import interfaces.Deque;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private final int DEFAULT_CAPACITY = 7;
    private int head;
    private int tail;
    private int size;
    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.head = this.tail = this.elements.length / 2;
    }

    @Override
    public void add(E element) {
        this.addLast(element);
    }

    @Override
    public void offer(E element) {
        this.addLast(element);
    }

    @Override
    public void addFirst(E element) {
        if (isEmpty()) {
            this.elements[this.tail] = element;
        } else {
            if (this.head == 0) {
                grow();
            }
            this.elements[--head] = element;
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        if (isEmpty()) {
            this.elements[this.tail] = element;
        } else {
            if (this.elements.length - 1 == this.tail) {
                grow();
            }
            this.elements[++this.tail] = element;
        }
        size++;
    }

    private void grow() {
        Object[] newElements = new Object[this.elements.length * 2 + 1];
        int startIndex = (newElements.length / 2) - (this.size / 2);
        int newHead = startIndex;
        int newTail = startIndex + this.size() - 1;

        for (int i = this.head; i <= this.tail; i++) {
            newElements[startIndex++] = this.elements[i];
        }

        this.head = newHead;
        this.tail = newTail;
        this.elements = newElements;
    }

    @Override
    public void push(E element) {
        this.addFirst(element);
    }

    @Override
    public void insert(int index, E element) {
        int realIndex = this.head + index;
        validateIndex(index, realIndex);
        if (isEmpty()) throw new IndexOutOfBoundsException();

        int middle = this.head + (this.size / 2);
        if (realIndex < middle) {
            if (this.head == 0) {
                grow();
                realIndex = this.head + index;
            }
            for (int i = this.head; i <= realIndex; i++) {
                this.elements[i - 1] = this.elements[i];
            }
            elements[realIndex - 1] = element;
            this.head--;
        } else {
            if (this.tail == this.elements.length - 1) {
                grow();
                realIndex = this.head + index;
            }
            for (int i = this.tail; i >= realIndex; i--) {
                this.elements[i + 1] = this.elements[i];
            }
            elements[realIndex] = element;
            this.tail++;
        }
        this.size++;
    }

    @Override
    public void set(int index, E element) {
        int realIndex = this.head + index;
        validateIndex(index, realIndex);
        if (isEmpty()) throw new IndexOutOfBoundsException();
        this.elements[realIndex] = element;
    }

    @Override
    public E peek() {
        if (!isEmpty()) {
            return getAt(this.head);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private E getAt(int index) {
        return (E) this.elements[index];
    }

    @Override
    public E poll() {
        return this.removeFirst();
    }

    @Override
    public E pop() {
        if (!isEmpty()) {
            E element = getAt(this.head);
            this.elements[this.head] = null;
            this.head++;
            this.size--;
            return element;
        }
        return null;
    }

    @Override
    public E get(int index) {
        if (isEmpty()) throw new IndexOutOfBoundsException();
        int realIndex = this.head + index;
        validateIndex(index, realIndex);
        return getAt(realIndex);
    }

    @Override
    public E get(Object object) {
        if (isEmpty()) return null;
        for (int i = this.head; i <= this.tail; i++) {
            if (this.elements[i].equals(object)) {
                return this.getAt(i);
            }
        }
        return null;
    }

    @Override
    public E remove(int index) {
        int realIndex = this.head + index;
        validateIndex(index, realIndex);
        if (isEmpty()) throw new IndexOutOfBoundsException();

        E element = getAt(realIndex);

        for (int i = realIndex; i < this.tail; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.removeLast();

        return element;
    }

    @Override
    public E remove(Object object) {
        if (isEmpty()) return null;
        for (int i = this.head; i <= this.tail; i++) {
            if (this.elements[i].equals(object)) {
                E element = this.getAt(i);

                for (int j = i; j < this.tail; j++) {
                    this.elements[j] = this.elements[j + 1];
                }
                removeLast();
                return element;
            }
        }
        return null;
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()) {
            E element = getAt(this.head);
            this.elements[this.head] = null;
            this.head++;
            this.size--;
            return element;
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (!isEmpty()) {
            E element = getAt(this.tail);
            this.elements[this.tail] = null;
            this.tail--;
            this.size--;
            return element;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        Object[] newElements = new Object[this.size];
        int newIndex = 0;

        for (int i = this.head; i <= this.tail; i++) {
            newElements[newIndex++] = this.elements[i];
        }

        this.elements = newElements;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = head;
            @Override
            public boolean hasNext() {
                return index <= tail;
            }

            @Override
            public E next() {
                return getAt(index++);
            }
        };
    }

    private void validateIndex(int index, int realIndex) {
        if ((index < 0 || realIndex > this.tail)) {
            throw new IndexOutOfBoundsException();
        }
    }
}
