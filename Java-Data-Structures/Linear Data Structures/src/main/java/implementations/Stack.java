package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E value) {
            this.element = value;
        }
    }

    private Node<E> top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> nodeToPush = new Node<>(element);

        nodeToPush.next = this.top;
        this.top = nodeToPush;

        this.size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");

        E element = this.top.element;

        this.top.next = this.top;
        this.size--;

        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");

        return top.element;
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
            Node<E> current = top;
            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public E next() {
                E value = current.element;

                current = current.next;

                return value;
            }
        };
    }
}
