package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    private Node<E> head;
    private int size;

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> toInsert = new Node<>(element);

        if (isEmpty()) {
            this.head = toInsert;
        } else {
            Node<E> current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = toInsert;
        }

        size++;
    }

    @Override
    public E poll() {
        if (isEmpty()) throw new IllegalStateException();

        E element = this.head.element;

        if (this.size == 1) {
            this.head = null;
        } else {
            Node<E> next = this.head.next;
            this.head.next = null;
            this.head = next;
        }

        size--;

        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new IllegalStateException();

        return this.head.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head;
            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public E next() {
                E element = current.element;

                current = current.next;

                return element;
            }
        };
    }
}
