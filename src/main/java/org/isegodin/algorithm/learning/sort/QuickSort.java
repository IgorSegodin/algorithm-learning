package org.isegodin.algorithm.learning.sort;

/**
 * @author i.segodin
 */
public class QuickSort {

    public static void sort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    private static void sort(int[] data, int start, int end) {
        if (start < end) {
            print(data);

            int p = partition(data, start, end);

            sort(data, start, p);
            sort(data, p + 1, end);
        }
    }

    private static int partition(int[] data, int start, int end) {
        int pivot = data[start];
        int i = start - 1;
        int j = end + 1;

        while (true) {
            do {
                i = i + 1;
            } while (data[i] < pivot);

            do {
                j = j - 1;
            } while (data[j] > pivot);

            if (i >= j) {
                return j;
            }

            swap(data, i, j);
        }
    }

    private static void swap(int[] data, int i1, int i2) {
        int tmp = data[i1];
        data[i1] = data[i2];
        data[i2] = tmp;
    }

    /*
    TODO fix lomuto
    Lomuto partition scheme
    algorithm quicksort(A, lo, hi) is
        if lo < hi then
            p := partition(A, lo, hi)
            quicksort(A, lo, p - 1 )
            quicksort(A, p + 1, hi)

    algorithm partition(A, lo, hi) is
        pivot := A[hi]
        i := lo - 1
        for j := lo to hi - 1 do
            if A[j] < pivot then
                i := i + 1
                swap A[i] with A[j]
        swap A[i + 1] with A[hi]
        return i + 1

     */

    /*
    Hoare partition scheme

    algorithm quicksort(A, lo, hi) is
        if lo < hi then
            p := partition(A, lo, hi)
            quicksort(A, lo, p)
            quicksort(A, p + 1, hi)

    algorithm partition(A, lo, hi) is
        pivot := A[lo]
        i := lo - 1
        j := hi + 1
        loop forever
            do
                i := i + 1
            while A[i] < pivot

            do
                j := j - 1
            while A[j] > pivot

            if i >= j then
                return j

            swap A[i] with A[j]

     */

    private static void print(int[] data) {
        System.out.println();
        for (int val : data) {
            System.out.print(" ");
            System.out.print(val);
        }
        System.out.println();
    }
}
