import java.util.function.Consumer;

class AATree<T extends Comparable<T>> {

    public static class Node<T> {
        private Node<T> left;
        private Node<T> right;
        private T element;
        private int level;

        public Node() {
        }

        public Node(T element, Node<T> left, Node<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.level = 1;
        }
    }

    private Node<T> root;

    public AATree() {
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void clear() {
        this.root = null;
    }

    public void insert(T element) {
        root = this.insert(element, this.root);
    }

    private Node<T> insert(T element, Node<T> node) {
        if (node == null) {
            node = new Node<>(element, null, null);
            return node;
        } else if (element.compareTo(node.element) < 0) {
            node.left = insert(element, node.left);
        } else if (element.compareTo(node.element) > 0) {
            node.right = insert(element, node.right);
        } else {
            return node;
        }

        node = skew(node);
        node = split(node);

        return node;
    }

    private Node<T> split(Node<T> node) {
        if (node == null) {
            return null;
        } else if (node.right == null || node.right.right == null) {
            return node;
        } else if (node.level == node.right.right.level) {
            Node<T> right = node.right;
            node.right = right.left;
            right.left = node;

            right.level++;
            return right;
        }

        return node;
    }

    private Node<T> skew(Node<T> node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        } else if (node.left.level == node.level) {
            Node<T> left = node.left;
            node.left = left.right;
            left.right = node;
            return left;
        }

        return node;
    }

    public int countNodes() {
        return countNodes(this.root);
    }

    private int countNodes(Node<T> node) {
        if (node == null) {
            return 0;
        }

        int count = 1;
        count += countNodes(node.left);
        count += countNodes(node.right);

        return count;
    }

    public boolean search(T element) {
        return search(element, this.root);
    }

    private boolean search(T element, Node<T> node) {
        boolean isFound = false;

        while (node != null && !isFound) {

            int cmp = element.compareTo(node.element);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                isFound = true;
                break;
            }

            isFound = this.search(element, node);
        }

        return isFound;
    }

    public void inOrder(Consumer<T> consumer) {
        this.inOrder(this.root, consumer);
    }

    private void inOrder(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            this.inOrder(node.left, consumer);
            consumer.accept(node.element);
            this.inOrder(node.right, consumer);
        }
    }

    public void preOrder(Consumer<T> consumer) {
        this.preOrder(this.root, consumer);
    }

    private void preOrder(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            consumer.accept(node.element);
            this.preOrder(node.left, consumer);
            this.preOrder(node.right, consumer);
        }
    }

    public void postOrder(Consumer<T> consumer) {
        this.postOrder(this.root, consumer);
    }

    private void postOrder(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            this.postOrder(node.left, consumer);
            this.postOrder(node.right, consumer);
            consumer.accept(node.element);
        }
    }
}