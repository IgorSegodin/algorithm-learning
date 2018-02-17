package org.isegodin.algorithm.learning.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author isegodin
 */
public class BinaryTreeTest {

    private BinaryTree<Integer> tree;

    @Before
    public void before() {
        tree = new BinaryTree<>();
    }

    @Test
    public void test_01() {
        Node<Integer> expectedNode =
                node(2,
                        node(3,
                                node(4),
                                null
                        ),
                        node(1)
                );

        Arrays.asList(1, 2, 3, 4).forEach(tree::add);

        assertNode(expectedNode, tree.getRootNode());
    }

    @Test
    public void test_02() {
        Node<Integer> expectedNode =
                node(2,
                        node(3,
                                node(4,
                                        node(5),
                                        null

                                ),
                                null
                        ),
                        node(1)
                );

        Arrays.asList(1, 2, 3, 4, 5).forEach(tree::add);

        assertNode(expectedNode, tree.getRootNode());
    }

    @Test
    public void test_03() {
        Node<Integer> expectedNode =
                node(3,
                        node(5,
                                node(6,
                                        node(7),
                                        null
                                ),
                                node(4)
                        ),
                        node(2,
                                null,
                                node(1)
                        )
                );

        Arrays.asList(1, 2, 3, 4, 5, 6, 7).forEach(tree::add);

        assertNode(expectedNode, tree.getRootNode());
    }

    @Test
    public void testParentAfterRotation() {
        Arrays.asList(
                6, 2, 1, 4, 3, 5
        ).forEach(tree::add);

        Node<Integer> rootNode = tree.getRootNode();
        assertParent(rootNode, rootNode.getLeft());
        assertParent(rootNode, rootNode.getRight());
    }

    /*
                      8
              /              \
             4               12
         /      \         /     \
        2       6       10     14
       / \     / \     / \    / \
      1  3    5  7    9 11   13 15
     */
    @Test
    public void testInOrderIterator() {
        Arrays.asList(
                8,
                4, 12,
                2, 14, 6, 10,
                1, 15, 3, 13, 5, 11, 7, 9
        ).forEach(tree::add);

        assertEquals(15, tree.size());

        Iterator<Integer> iterator = tree.iterator();
        for (int i = 1; i <= 15; i++) {
            assertEquals(i, (int) iterator.next());
        }

        Assert.assertFalse(iterator.hasNext());
    }


    /*
               6
            /     \
          2        8
         / \     /  \
        1   4   7   10
           / \     / \
          3  5    9  11
     */
    @Test
    public void testInOrderIterator2() {
        Arrays.asList(
                6, 2, 1, 4, 3, 5, 8, 7, 10, 9, 11
        ).forEach(tree::add);

        assertEquals(11, tree.size());

        Iterator<Integer> iterator = tree.iterator();
        for (int i = 1; i <= 11; i++) {
            Integer next = iterator.next();
            System.out.println(next);
            assertEquals(i, (int) next);
        }

        Assert.assertFalse(iterator.hasNext());
    }

    private <T> void assertNode(Node<T> expected, Node<T> actual) {
        if (expected == null && actual == null) {
            return;
        } else if (expected == null) {
            fail("Expected null, but found " + actual.getData());
        } else if (actual == null) {
            fail("Expected value, but found null " + expected.getData());
        }
        assertEquals(expected.getData(), actual.getData());

        assertNode(expected.getLeft(), actual.getLeft());
        assertNode(expected.getRight(), actual.getRight());
    }

    private void assertParent(Node expectedParent, Node childNode) {
        if (childNode == null) {
            return;
        }
        assertEquals(expectedParent.getData(), childNode.getParent().getData());

        assertParent(childNode, childNode.getLeft());
        assertParent(childNode, childNode.getRight());
    }

    private <T> TestNode<T> node(T val) {
        return node(val, null, null);
    }

    private <T> TestNode<T> node(T val, TestNode<T> right, TestNode<T> left) {
        TestNode<T> node = new TestNode<>(val, left, right);

        if (left != null) {
            left.setParent(node);
        }
        if (right != null) {
            right.setParent(node);
        }
        return node;
    }

    private static class TestNode<T> implements Node<T> {

        private T data;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;

        public TestNode(T data) {
            this.data = data;
        }

        public TestNode(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public T getData() {
            return data;
        }

        @Override
        public Node<T> getParent() {
            return parent;
        }

        @Override
        public Node<T> getLeft() {
            return left;
        }

        @Override
        public Node<T> getRight() {
            return right;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }
    }

}
