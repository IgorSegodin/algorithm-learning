package org.isegodin.algorithm.learning.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author isegodin
 */
public class BinaryTree<T> implements Tree<T> {

    private TreeNode<T> rootNode;

    private Comparator<T> comparator;

    private int size = 0;

    public BinaryTree() {
        comparator = (a, b) -> {
            if (a == b) {
                return 0;
            }
            return b == null ? 1 : ((Comparable) a).compareTo(b);
        };
    }

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T item) {
        if (rootNode == null) {
            rootNode = new TreeNode<>(item, null);
            size += 1;
            return;
        }

        TreeNode<T> node = rootNode;
        int compare;
        while (true) {
            compare = comparator.compare(node.getData(), item);
            if (compare > 0 && node.getLeft() != null) {
                node = node.getLeft();
            } else if (compare < 0 && node.getRight() != null) {
                node = node.getRight();
            } else {
                break;
            }
        }

        if (compare == 0) {
            node.setData(item);
            return;
        }

        size += 1;

        if (compare > 0) {
            node.setLeft(new TreeNode<>(item, node));
        } else {
            node.setRight(new TreeNode<>(item, node));
        }
    }

    public void delete(T item) {
    }

    public boolean contains(T item) {
        return false;
    }

    public Node<T> getRootNode() {
        if (rootNode != null) {
            return new ReadOnlyNode<>(rootNode);
        }
        return null;
    }

    public void balance() {
        List<T> tempList = new ArrayList<>(size);
        for (T data : this) {
            tempList.add(data);
        }

        rootNode = null;
        size = 0;

        int middle = (int) Math.ceil(tempList.size() / 2.0);
        add(tempList.get(middle));
        for (int i = middle - 1, j = middle + 1; i >= 0 || j < tempList.size(); i--, j++) {
            if (i >= 0) {
                add(tempList.get(i));
            }
            if (j < tempList.size()) {
                add(tempList.get(j));
            }
        }
    }

    public int size() {
        return size;
    }

    public int getOrder() {
        int order = 0;
        List<TreeNode<T>> level = new ArrayList<>();
        level.add(rootNode);

        while (!level.isEmpty()) {
            order += 1;
            List<TreeNode<T>> nextLevel = new ArrayList<>();
            for (TreeNode<T> node : level) {
                if (node.getLeft() != null) {
                    nextLevel.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    nextLevel.add(node.getRight());
                }
            }
            level = nextLevel;
        }

        return order;

    }

    private TreeNode<T> findFirstNode(TreeNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public Iterator<T> iterator() {
        TreeNode<T> node = null;
        if (rootNode != null) {
            node = findFirstNode(rootNode);
        }
        return new TreeIterator(node);
    }

    private class TreeIterator implements Iterator<T> {

        private TreeNode<T> current;

        public TreeIterator(TreeNode<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T next = current.getData();
            current = findNext(current);
            return next;
        }

        private TreeNode<T> findNext(TreeNode<T> node) {
            if (node.getRight() != null) {
                return findFirstNode(node.getRight());
            }
            if (node.getParent() == null) {
                return null;
            }
            TreeNode<T> parent = node.getParent();

            if (node == parent.getLeft()) {
                return parent;
            }

            while (parent.getParent() != null) {
                if (parent.getParent().getLeft() == parent) {
                    return parent.getParent();
                }
                parent = parent.getParent();
            }

            return null;
        }
    }
}
