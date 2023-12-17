package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    private Node<E> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> toInsert = new Node<>(element);

        toInsert.next = this.head;
        this.head = toInsert;

        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> toInsert = new Node<>(element);

        if (isEmpty()) {
            this.head = toInsert;
        } else {
            Node<E> current = this.head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = toInsert;
        }

        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) throw new IllegalStateException();

        E element = this.head.element;

        this.head = this.head.next;

        size--;

        return element;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) throw new IllegalStateException();

        E element = this.head.element;

        if (this.size == 1) {
            this.head = null;
        } else {
            Node<E> preLast = this.head;
            Node<E> last = this.head.next;

            while (last.next != null) {
                preLast = preLast.next;
                last = last.next;
            }
            element = last.element;
            preLast.next = null;
        }

        size--;

        return element;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) throw new IllegalStateException();

        return this.head.element;
    }

    @Override
    public E getLast() {
        if (isEmpty()) throw new IllegalStateException();

        Node<E> current = this.head;

        while (current.next != null) {
            current = current.next;
        }

        return current.element;
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
