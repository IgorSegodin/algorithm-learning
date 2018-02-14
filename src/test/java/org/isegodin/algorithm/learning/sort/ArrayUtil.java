package org.isegodin.algorithm.learning.sort;

/**
 * @author i.segodin
 */
public class ArrayUtil {

    public static void print(int[] data) {
        System.out.println();
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
