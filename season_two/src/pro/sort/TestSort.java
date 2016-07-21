package pro.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Near on 2015/11/29.
 */
public class TestSort {
    @Test
    public void test() {
        int[] arr = {5, 4, 3, 2, 1};
        bubbleSort(arr);

        int[] arr2 = {5, 1, 2, 3, 4};
        bubbleSortPlus(arr2);

        int[] arr3 = {5, 4, 3, 2, 1};
        choiceSort(arr3);
    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 加强版冒泡排序
     *
     * @param arr
     */
    public void bubbleSortPlus(int[] arr) {
        boolean ordered = true;
        out:
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    ordered = false;
                }
            }
            if (ordered) {
                break out;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public void choiceSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
