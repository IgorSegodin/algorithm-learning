package org.isegodin.algorithm.learning.tree;

/**
 * @author isegodin
 */
public interface Tree<T> extends Iterable<T> {

    void add(T item);

    Node<T> getRootNode();
}
