package com.rent.mytestapplication.homework;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * Created by RenTao on 17/2/26.
 */
public class Daily {

    /**
     * 二分法
     */
    @Test
    public void splitFind() {
        int[] arr = randomArray(10);
        Arrays.sort(arr);
        System.out.println("origin: " + Arrays.toString(arr));
        int target = (int) (Math.random() * arr.length);
        System.out.println("target: " + target);
        int start = 0, end = arr.length;
        int mid = arr.length / 2;
        int index = -1;
        while (end > start) {
            System.out.println("arr[mid]: " + arr[mid] + ", start: " + start + ", mid: " + mid + ", end: " + end);
            if (target < arr[mid]) {
                end = mid - 1;
                mid = (start + end) / 2;
            } else if (target > arr[mid]) {
                start = mid + 1;
                mid = (start + end) / 2;
            } else {
                index = mid;
                break;
            }
            System.out.println("start: " + start + ", mid: " + mid + ", end: " + end);
        }
        System.out.println("index: " + index);
    }

    /**
     * 冒泡排序
     */
    @Test
    public void testBubbling() {
        int[] arr = randomArray(10);
        bubbling(arr, true);
        bubbling(arr, false);
    }

    private void bubbling(int[] arr, boolean asc) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if ((asc && arr[j] > arr[j + 1]) // 升序
                        || (!asc && arr[j] < arr[j + 1])) { // 降序
                    swap(arr, j, j + 1);
                }
            }
        }
        System.out.print(asc ? "asc: " : "desc: ");
        System.out.println(Arrays.toString(arr));
    }

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
        if (end - start <= 0) {
            return;
        }
        int keyIndex = splitArray(arr, start, end);
        quickSort(arr, start, keyIndex - 1);
        quickSort(arr, keyIndex + 1, end);
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

    /**
     * 从扑克牌中随机抽 5 张牌,判断是不是顺子,即这 5 张牌是不是连续的。
     * 2-10 为数字本身,A 为 1,J 为 11,Q 为 12,K 为 13,而大小王可以看成任意的数字。
     */
    @Test
    public void random5() {

    }
}
