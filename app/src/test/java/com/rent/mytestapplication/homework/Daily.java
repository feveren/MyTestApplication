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
        System.out.println("origin: " + Arrays.toString(arr));
        int start = 0, end = arr.length - 1;
        quickSort(arr, start, end);
    }

    private void quickSort(int[] arr, int start, int end) {
        int keyIndex = splitArray(arr, start, end);
        if (keyIndex - start > 1) {
            quickSort(arr, start, keyIndex - 1);
        }
        if (end - keyIndex > 1) {
            quickSort(arr, keyIndex + 1, end);
        }
    }

    private int splitArray(int[] arr, int start, int end) {
        int keyIndex = start, key = arr[keyIndex];
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

    @Test
    public void lowerAndUpper() {
        String str = "OkhaoPingCeilXu";
        // 复杂度O(n)
        char[] arr = str.toCharArray();
        char[] newArr = new char[arr.length];
        int index = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] >= 'A' && arr[i] <= 'Z') {
                newArr[index--] = arr[i];
            }
        }
        index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                newArr[index++] = arr[i];
            }
        }
        System.out.println(newArr);
    }
}
