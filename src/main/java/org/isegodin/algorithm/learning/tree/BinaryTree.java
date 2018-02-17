package org.isegodin.algorithm.learning.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author isegodin
 */
public class BinaryTree<T> implements Tree<T> {

    private static final int MAX_BALANCE_DIFFERENCE = 2;

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

    @Override
    public void add(T item) {
        if (rootNode == null) {
            rootNode = new TreeNode<>(item, null);
            size += 1;
            return;
        }

        TreeNode<T> node = rootNode;
        int compare;
        while (true) {
            int weightBalance = node.getLeftWeight() - node.getRightWeight();
            if (weightBalance <= -MAX_BALANCE_DIFFERENCE) {
                node = rotateLeft(node);
            } else if (weightBalance >= MAX_BALANCE_DIFFERENCE) {
                node = rotateRight(node);
            }

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
            node.setLeftWeight(1);
        } else {
            node.setRight(new TreeNode<>(item, node));
            node.setRightWeight(1);
        }

        updateWeightUpwards(node);
    }

    private void updateWeightUpwards(TreeNode<T> node) {
        TreeNode<T> parentNode;
        while ((parentNode = node.getParent()) != null) {
            if (parentNode.getLeft() != null) {
                parentNode.setLeftWeight(parentNode.getLeft().getSiblingsWeight() + 1);
            } else {
                parentNode.setLeftWeight(0);
            }

            if (parentNode.getRight() != null) {
                parentNode.setRightWeight(parentNode.getRight().getSiblingsWeight() + 1);
            } else {
                parentNode.setRightWeight(0);
            }
            node = parentNode;
        }
    }

    private TreeNode<T> rotateRight(TreeNode<T> root) {
        TreeNode<T> newRoot = root.getLeft();

        TreeNode<T> leftRight = root.getLeft().getRight();

        if (leftRight != null) {
            leftRight.setParent(root);
            root.setLeft(leftRight);
            root.setLeftWeight(leftRight.getSiblingsWeight() + 1);
        } else {
            root.setLeft(null);
            root.setLeftWeight(0);
        }

        newRoot.setRight(root);
        newRoot.setRightWeight(root.getSiblingsWeight() + 1);

        updateParentAfterRotation(root, newRoot);

        return newRoot;
    }

    private TreeNode<T> rotateLeft(TreeNode<T> root) {
        TreeNode<T> newRoot = root.getRight();

        TreeNode<T> rightLeft = root.getRight().getLeft();

        if (rightLeft != null) {
            rightLeft.setParent(root);
            root.setRight(rightLeft);
            root.setRightWeight(rightLeft.getSiblingsWeight() + 1);
        } else {
            root.setRight(null);
            root.setRightWeight(0);
        }

        newRoot.setLeft(root);
        newRoot.setLeftWeight(root.getSiblingsWeight() + 1);

        updateParentAfterRotation(root, newRoot);

        return newRoot;
    }

    private void updateParentAfterRotation(TreeNode<T> oldRoot, TreeNode<T> newRoot) {
        TreeNode<T> parent = oldRoot.getParent();
        if (parent == null) {
            rootNode = newRoot;
        } else if (parent.getLeft() == oldRoot) {
            parent.setLeft(newRoot);
            parent.setLeftWeight(newRoot.getSiblingsWeight() + 1);
        } else {
            parent.setRight(newRoot);
            parent.setRightWeight(newRoot.getSiblingsWeight() + 1);
        }
        newRoot.setParent(parent);
        oldRoot.setParent(newRoot);
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
            if (current == null) {
                throw new NoSuchElementException();
            }

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
