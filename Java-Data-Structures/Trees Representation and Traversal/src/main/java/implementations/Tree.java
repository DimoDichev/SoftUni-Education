package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            child.parent = this;
            this.children.add(child);
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();

        if (this.value == null) {
            return result;
        }

        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            result.add(current.value);
            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();

        this.dfs(this, result);

        return result;
    }

    private void dfs(Tree<E> node, List<E> result) {
        for (Tree<E> child : node.children) {
            dfs(child, result);
        }
        result.add(node.value);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> parent = findNode(parentKey);

        if (parent == null) {
            throw new IllegalArgumentException();
        }

        child.parent = parent;
        parent.children.add(child);
    }

    private Tree<E> findNode(E parentKey) {
        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> node = queue.poll();

            if (node.value.equals(parentKey)) {
                return node;
            }

            for (Tree<E> child : node.children) {
                queue.offer(child);
            }
        }

        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> node = findNode(nodeKey);

        if (node == null) {
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : node.children) {
            child.parent = null;
        }

        node.children.clear();

        if (node.parent == null) {
            this.value = null;
            return;
        }

        node.parent.children.remove(node);
        node.parent = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = findNode(firstKey);
        Tree<E> secondNode = findNode(secondKey);

        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;

        if (firstParent == null) {
            secondNode.parent = null;
            this.value = secondNode.value;
            this.children = secondNode.children;
            return;
        } else if (secondParent == null) {
            firstNode.parent = null;
            this.value = firstNode.value;
            this.children = firstNode.children;
            return;
        }

        int firstNodeIndex = firstParent.children.indexOf(firstNode);
        int secondNodeIndex = secondParent.children.indexOf(secondNode);

        firstParent.children.set(firstNodeIndex, secondNode);
        secondParent.children.set(secondNodeIndex, firstNode);

    }
}



