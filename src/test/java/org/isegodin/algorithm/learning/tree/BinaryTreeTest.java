package org.isegodin.algorithm.learning.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/**
 * @author isegodin
 */
public class BinaryTreeTest {

    private BinaryTree<Integer> tree;

    @Before
    public void before() {
        tree = new BinaryTree<>();
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
    public void testInOrderIterator() {
        tree.add(6);
        tree.add(2);
        tree.add(1);
        tree.add(4);
        tree.add(3);
        tree.add(5);
        tree.add(8);
        tree.add(7);
        tree.add(10);
        tree.add(9);
        tree.add(11);

        tree.print();

        tree.balance();

        Assert.assertEquals(11, tree.size());

        Iterator<Integer> iterator = tree.iterator();
        for (int i = 1; i <= 11; i++) {
            Assert.assertTrue(i == iterator.next());
        }

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testBalancing() {
        for (int i = 0; i < 10; i++) {
            tree.add(i);
        }

        tree.balance();

        for (Integer integer : tree) {
            System.out.println(integer);
        }
    }
}
