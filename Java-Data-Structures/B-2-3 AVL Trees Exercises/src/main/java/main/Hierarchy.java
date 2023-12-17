package main;

import java.util.*;
import java.util.stream.Collectors;

public class Hierarchy<T> implements IHierarchy<T> {

    private Map<T, HierarchyNode<T>> data;
    private HierarchyNode<T> root;

    public Hierarchy(T element) {
        this.data = new HashMap<>();
        HierarchyNode<T> node = new HierarchyNode<>(element);
        data.put(element, node);
        root = node;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public void add(T element, T child) {
        HierarchyNode<T> parent = getNode(element);

        if (data.containsKey(child)) {
            throw new IllegalArgumentException();
        }

        HierarchyNode<T> toBeAdded = new HierarchyNode<>(child);
        toBeAdded.setParent(parent);

        parent.getChildren().add(toBeAdded);
        data.put(child, toBeAdded);
    }

    @Override
    public void remove(T element) {
        HierarchyNode<T> toRemove = getNode(element);
        HierarchyNode<T> parent = toRemove.getParent();

        if (parent == null) {
            throw new IllegalStateException();
        }

        parent.getChildren().remove(toRemove);
        parent.getChildren().addAll(toRemove.getChildren());
        toRemove.getChildren().forEach(e -> e.setParent(parent));
        toRemove.setParent(null);

        data.remove(element);
    }

    @Override
    public Iterable<T> getChildren(T element) {
        HierarchyNode<T> node = getNode(element);
        return node.getChildren().stream().map(HierarchyNode::getElement).collect(Collectors.toList());
    }

    @Override
    public T getParent(T element) {
        HierarchyNode<T> node = getNode(element);
        return node.getParent() == null ? null : node.getParent().getElement();
    }

    @Override
    public boolean contains(T element) {
        return data.containsKey(element);
    }

    @Override
    public Iterable<T> getCommonElements(IHierarchy<T> other) {
        List<T> elements = new ArrayList<>();
        this.data.keySet().forEach(k -> {
            if (other.contains(k)) {
                elements.add(k);
            }
        });
        return elements;
    }

    @Override
    public Iterator<T> iterator() {

        ArrayDeque<HierarchyNode<T>> queue = new ArrayDeque<>();
        HierarchyNode<T> node = root;
        queue.add(node);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                HierarchyNode<T> node = queue.poll();
                node.getChildren().forEach(queue::offer);
                return node.getElement();
            }
        };
    }

    private HierarchyNode<T> getNode(T element) {
        HierarchyNode<T> node = data.get(element);
        if (node == null) {
            throw new IllegalArgumentException();
        }
        return node;
    }
}
