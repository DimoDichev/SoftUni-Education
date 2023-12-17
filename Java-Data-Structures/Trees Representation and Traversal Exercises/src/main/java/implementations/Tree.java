package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.value;
    }

    @Override
    public String getAsString() {
        StringBuilder builder = new StringBuilder();
        int[] stepping = new int[]{0};

        traverseTreeDFSWithStepping(builder, stepping, 0, this);

        return builder.toString().trim();
    }

    private void traverseTreeDFSWithStepping(StringBuilder builder, int[] stepping, int step, Tree<E> tree) {
        if (stepping[0] != step) stepping[0] = step;

        builder.append(steppingAsString(stepping, tree));

        for (Tree<E> child : tree.children) {
            traverseTreeDFSWithStepping(builder, stepping, step + 2, child);
        }
    }

    private String steppingAsString(int[] stepping, Tree<E> tree) {
        return " ".repeat(Math.max(0, stepping[0])) + tree.getKey() + System.lineSeparator();
    }

    @Override
    public List<E> getLeafKeys() {
        List<E> leafNodes = new ArrayList<>();

        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();

            for (Tree<E> child : tree.children) {
                queue.offer(child);
                if (child.children.isEmpty()) {
                    leafNodes.add(child.value);
                }
            }
        }

        return leafNodes;
    }

    @Override
    public List<E> getMiddleKeys() {
        List<E> middleNodes = new ArrayList<>();

        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();

            for (Tree<E> child : tree.children) {
                queue.offer(child);
                if (!child.children.isEmpty()) {
                    middleNodes.add(child.value);
                }
            }
        }

        return middleNodes;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> leafNodes = new ArrayList<>();
        findLeafNodes(leafNodes);

        Tree<E> result = this;
        int deeps = 0;

        for (Tree<E> node : leafNodes) {
            int currentDeep = findDeep(node);

            if (currentDeep > deeps) {
                result = node;
                deeps = currentDeep;
            }
        }

        return result;
    }

    private int findDeep(Tree<E> node) {
        int deep = 0;

        Tree<E> current = node;

        while (current.parent != null) {
            deep++;
            current = current.parent;
        }

        return deep;
    }

    private void findLeafNodes(List<Tree<E>> nodes) {
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();

            for (Tree<E> child : tree.children) {
                queue.offer(child);
                if (child.children.isEmpty()) nodes.add(child);
            }
        }
    }

    @Override
    public List<E> getLongestPath() {
        Tree<E> deepestNode = getDeepestLeftmostNode();
        List<E> result = new ArrayList<>();

        Tree<E> current = deepestNode;

        while (current.parent != null) {
            result.add(current.value);
            current = current.parent;
        }
        result.add(current.value);

        Collections.reverse(result);

        return result;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<Tree<E>> nodes = new ArrayList<>();
        findLeafNodes(nodes);

        List<List<E>> result = new ArrayList<>();

        for (Tree<E> node : nodes) {
            int pathSum = findSum(node);

            if (pathSum == sum) {
                Tree<E> current = node;
                List<E> pathList = new ArrayList<>();

                while (current.parent != null) {
                    pathList.add(current.value);
                    current = current.parent;
                }
                pathList.add(current.value);

                Collections.reverse(pathList);
                result.add(pathList);
            }
        }

        return result;
    }

    private int findSum(Tree<E> node) {
        int sum = 0;

        Tree<E> current = node;

        while (current.parent != null) {
            sum = sum + (int) current.value;

            current = current.parent;
        }

        return sum + (int) current.value;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> result = new ArrayList<>();

        List<Tree<E>> allNodes = new ArrayList<>();
        getAllNodes(this, allNodes);

        for (Tree<E> node : allNodes) {
            int subtreeSum = (int) node.value;

            for (Tree<E> child : node.children) {
                subtreeSum = subtreeSum + (int) child.value;
            }

            if (subtreeSum == sum) {
                result.add(node);
            }
        }

        return result;
    }

    private void getAllNodes(Tree<E> node, List<Tree<E>> allNodes) {

        allNodes.add(node);

        for (Tree<E> child : node.children) {
            getAllNodes(child, allNodes);
        }
    }
}



