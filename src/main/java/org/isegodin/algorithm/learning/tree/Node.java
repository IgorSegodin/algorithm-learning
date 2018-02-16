package org.isegodin.algorithm.learning.tree;

/**
 * @author isegodin
 */
public interface Node<T> {

    T getData();

    Node<T> getParent();

    Node<T> getLeft();

    Node<T> getRight();
}
