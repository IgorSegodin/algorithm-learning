package org.isegodin.algorithm.learning.tree;

/**
 * @author isegodin
 */
public class ReadOnlyNode<T> implements Node<T> {

    private final Node<T> origin;

    public ReadOnlyNode(Node<T> origin) {
        this.origin = origin;
    }

    @Override
    public T getData() {
        return origin.getData();
    }

    @Override
    public Node<T> getParent() {
        if (origin.getParent() != null) {
            return new ReadOnlyNode<>(origin.getParent());
        }
        return null;
    }

    @Override
    public Node<T> getLeft() {
        if (origin.getLeft() != null) {
            return new ReadOnlyNode<>(origin.getLeft());
        }
        return null;
    }

    @Override
    public Node<T> getRight() {
        if (origin.getRight() != null) {
            return new ReadOnlyNode<>(origin.getRight());
        }
        return null;
    }
}
