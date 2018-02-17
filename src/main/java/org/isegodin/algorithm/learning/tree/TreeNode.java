package org.isegodin.algorithm.learning.tree;

import lombok.Data;
import lombok.ToString;

/**
 * @author isegodin
 */

@Data
@ToString(exclude = {"parent"})
public class TreeNode<T> implements Node<T> {

    private T data;
    private TreeNode<T> parent;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private int leftWeight;
    private int rightWeight;

    public TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public int getSiblingsWeight() {
        return leftWeight + rightWeight;
    }
}
