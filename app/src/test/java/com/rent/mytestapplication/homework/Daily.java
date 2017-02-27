package com.rent.mytestapplication.homework;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by RenTao on 17/2/26.
 */

public class Daily {

    @Test
    public void findLongestString() {
        final String str = "abbcaecdbb";
        char[] chars = str.toCharArray();
        String result = null;
        StringBuilder temp = new StringBuilder();
        for (char c : chars) {
            if (temp.indexOf(String.valueOf(c)) >= 0) {
                if (result == null || temp.length() > result.length()) {
                    result = temp.toString();
                }
                temp.delete(0, temp.length());
            }
            temp.append(c);
        }
        System.out.println(result);
    }

    /**
     * 快速排序
     */
    @Test
    public void testQuickSort() {
        int[] arr = randomArray(10);
        int keyIndex = 0;
        int start = 0, end = arr.length - 1, key = arr[keyIndex];
        System.out.println("origin: " + Arrays.toString(arr));
        quickSort(arr, keyIndex, start, end, key);
    }

    private void quickSort(int[] arr, int keyIndex, int start, int end, int key) {
        keyIndex = splitArray(arr, keyIndex, start, end, key);
        if (keyIndex - start > 1) {
            quickSort(arr, start, start, keyIndex - 1, arr[start]);
        }
        if (end - keyIndex > 1) {
            quickSort(arr, keyIndex + 1, keyIndex + 1, end, arr[keyIndex + 1]);
        }
    }

    private int splitArray(int[] arr, int keyIndex, int start, int end, int key) {
        while (start != end) {
            while (end > start) {
                if (key > arr[end]) {
                    swap(arr, keyIndex, end);
                    keyIndex = end;
                    break;
                }
                end--;
            }

            while (end > start) {
                if (key < arr[start]) {
                    swap(arr, keyIndex, start);
                    keyIndex = start;
                    break;
                }
                start++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return keyIndex;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int[] randomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (size * Math.random());
        }
        return arr;
    }
}
