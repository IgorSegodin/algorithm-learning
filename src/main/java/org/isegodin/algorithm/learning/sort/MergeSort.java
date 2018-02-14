package org.isegodin.algorithm.learning.sort;

/**
 * @author i.segodin
 */
public class MergeSort {

    public static void sort(int[] data) {
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
        int[] merged = new int[end - start + 1];

        int length = end - start + 1;

        int left = start;
        int right = mid + 1;

        for (int i = 0; i < length; i++) {
            if (left > mid) {
                merged[i] = input[right++];
            } else if (right > end) {
                merged[i] = input[left++];
            } else if (input[left] > input[right]) {
                merged[i] = input[right++];
            } else {
                merged[i] = input[left++];
            }
        }
        System.arraycopy(merged, 0, input, start, merged.length);
    }
}
