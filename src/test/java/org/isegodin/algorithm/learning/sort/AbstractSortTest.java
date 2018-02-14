package org.isegodin.algorithm.learning.sort;

import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author i.segodin
 */
public abstract class AbstractSortTest {

    protected Consumer<int[]> sortAlgorithm;

    public AbstractSortTest(Consumer<int[]> sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    @Test
    public void testEmptyData() {
        int[] data = new int[0];
        sortAlgorithm.accept(data);
        Assert.assertTrue(data.length == 0);
    }

    @Test
    public void testEven() {
        int[] data = new int[]{2, 4, 1, 3};
        sortAlgorithm.accept(data);
        Assert.assertEquals(4, data.length);

        Assert.assertArrayEquals(new int[]{1, 2, 3, 4}, data);
    }

    @Test
    public void testOdd() {
        int[] data = new int[]{2, 4, 5, 1, 3};
        sortAlgorithm.accept(data);
        Assert.assertEquals(5, data.length);

        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, data);
    }

    @Test
    public void testMoreData() {
        int[] data = new int[]{3, 6, 1, 10, 5, 2, 9, 8, 7, 4};
        sortAlgorithm.accept(data);
        Assert.assertEquals(10, data.length);

        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, data);
    }

    @Test
    public void testAlreadySortedData() {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        sortAlgorithm.accept(data);
        Assert.assertEquals(10, data.length);

        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, data);
    }
}
