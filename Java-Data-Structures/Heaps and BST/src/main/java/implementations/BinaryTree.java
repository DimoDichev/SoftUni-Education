package implementations;

import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree() {
    }

    public BinaryTree(E key, BinaryTree<E> left, BinaryTree<E> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder builder = new StringBuilder();

        builder.append(createPadding(indent)).append(this.key);

        if (this.getLeft() != null) {
            builder.append(System.lineSeparator()).append(this.getLeft().asIndentedPreOrder(indent + 2));
        }
        if (this.right != null) {
            builder.append(System.lineSeparator()).append(this.getRight().asIndentedPreOrder(indent + 2));
        }

        return builder.toString();
    }

    private String createPadding(int indent) {
        return " ".repeat(Math.max(0, indent));
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        preOrderTree(this, result);
        return result;
    }

    private void preOrderTree(BinaryTree<E> node, List<AbstractBinaryTree<E>> result) {
        if (node != null) {
            result.add(node);
            preOrderTree(node.left, result);
            preOrderTree(node.right, result);
        }
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        inOrderTree(this, result);
        return result;
    }

    private void inOrderTree(BinaryTree<E> node, List<AbstractBinaryTree<E>> result) {
        if (node != null) {
            inOrderTree(node.left, result);
            result.add(node);
            inOrderTree(node.right, result);
        }
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        postOrderTree(this, result);
        return result;
    }

    private void postOrderTree(BinaryTree<E> node, List<AbstractBinaryTree<E>> result) {
        if (node != null) {
            postOrderTree(node.left, result);
            postOrderTree(node.right, result);
            result.add(node);
        }
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (this.getLeft() != null) {
            this.getLeft().forEachInOrder(consumer);
        }
        consumer.accept(this.getKey());
        if (this.getRight() != null) {
            this.getRight().forEachInOrder(consumer);
        }
    }
}
