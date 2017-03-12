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
    public void dichotomie() {
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
    public void testBubbleSort() {
        int[] arr = randomArray(10);
        bubbleSort(arr, true);
        bubbleSort(arr, false);
    }

    private void bubbleSort(int[] arr, boolean asc) {
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
    public void fivePokers() {
        isStraight(new int[] { 6, 7, 8, 10, 13 });
        isStraight(new int[] { 9, 11, 8, 10, 12 });

        isStraight(new int[] { 6, 7, 3, 0, 5 });
        isStraight(new int[] { 6, 7, 8, 0, 5 });
        isStraight(new int[] { 6, 7, 8, 0, 13 });
    }

    private void isStraight(int[] picked) {
        System.out.println("picked: " + Arrays.toString(picked));
        bubbleSort(picked, true);
        // 是否有王牌
        boolean ghost = false;
        // 是否是顺子
        boolean straight = true;
        int length = picked.length - 1;
        for (int i = 0; i < length; i++) {
            if (i == 0 && picked[i] == 0) {
                ghost = true;
            } else if (picked[i] + 1 == picked[i + 1]) {

            } else if (ghost && i < length - 1) {
                ghost = false;
            } else {
                straight = false;
                break;
            }
        }
        System.out.println("picked is" + (straight ? "" : " not") + " straight");
    }

    /**
     * 假设有一支股票，在过去的一天里看到一组价格(单位;元)分别为[6,3,9,2,5,9,1,4,6]。
     * 根据这组数据计算出，什么时候买入，什么时候卖出收益最大？比如这题答案为下标为3(2元)买入，下标为5(9元)的时候卖出，收益最大。
     */
    @Test
    public void day12() {
//        int[] arr = { 6, 7, 9, 8, 5, 9, 2, 8, 6 };
//        int[] arr = { 6, 7, 9, 8, 5, 9, 2, 1, 6 };
        int[] arr = randomArray(8);
        System.out.println(Arrays.toString(arr));
        int low = -1, high = -1, tempLow = -1, tempHigh = -1;
        for (int i = 0; i < arr.length; i++) {
            if (tempLow < 0 || tempLow > arr[i]) {
                tempLow = arr[i];
            } else if (tempHigh < 0 || (arr[i] - tempLow > high - low)) {
                low = tempLow;
                high = tempHigh = arr[i];
            }
//            System.out.println("tempLow: " + tempLow + " tempHigh: " + tempHigh + " low: " + low + " high: " + high);
        }
        System.out.println(low + " " + high);
    }
}
