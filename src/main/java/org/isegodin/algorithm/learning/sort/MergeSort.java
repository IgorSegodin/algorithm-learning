package org.isegodin.algorithm.learning.sort;

/**
 * @author i.segodin
 */
public class MergeSort {

    public static void sort(int[] data) {
//        int[] copy = new int[data.length];
//        System.arraycopy(data, 0, copy, 0, data.length);
//        sort(data, 0, data.length - 1, copy);
        sort(data, 0, data.length - 1);
    }

    private static void sort(int[] input, int start, int end) {
        int size = end - start + 1;
        if (size < 2) {
            return;
        }

        int mid = (end + start) / 2;

        sort(input, start, mid);
        sort(input, mid + 1, end);

        merge(input, start, mid, end);
    }

    private static void merge(int[] input, int start, int mid, int end) {
        int p = start;
        int q = mid + 1;

        int[] Arr = new int[end - start + 1];
        int k = 0;

        for (int i = start; i <= end; i++) {
            if (p > mid)      //checks if first part comes to an end or not .
                Arr[k++] = input[q++];

            else if (q > end)   //checks if second part comes to an end or not
                Arr[k++] = input[p++];

            else if (input[p] < input[q])     //checks which part has smaller element.
                Arr[k++] = input[p++];

            else
                Arr[k++] = input[q++];
        }

        for (int i = 0; i < k; i++) {
           /* Now the real array has elements in sorted manner including both
                parts.*/
            input[start++] = Arr[i];
        }
    }

//    private static void merge(int[] input, int start, int mid, int end) {
//        int s1 = start;
//        int l1 = mid - s1;
//
//        int s2 = mid + 1;
//        int l2 = end - s2;
//
//
//        for (int i = 0; i <= (l1 > l2 ? l1 : l2); i++) {
//            int v1 = input[s1 + i];
//            int v2 = input[s2 + i];
//            if (v1 > v2) {
//                swap(input, s1 + i, s2 + i);
//            }
//        }
//    }


    private static void swap(int[] data, int i1, int i2) {
        int tmp = data[i1];
        data[i1] = data[i2];
        data[i2] = tmp;
    }

}
