package com.data_structure_and_algorithm.sort_tool;

import java.util.Arrays;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/31
 */
public class SortTool {

    // 冒泡排序
    public static void bubbleSort(int[] nums) {
        // 外循环：待排序元素数量为 n-1, n-2, ..., 1
        for (int i = nums.length - 1; i > 0; i--) {
            // 内循环：冒泡操作
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // 交换 nums[j] 与 nums[j + 1]
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }

    // 插入排序
    public static void insertionSort(int[] nums) {
        // 外循环：base = nums[1], nums[2], ..., nums[n-1]
        for (int i = 1; i < nums.length; i++) {
            int base = nums[i], j = i - 1;
            // 内循环：将 base 插入到左边的正确位置
            /*
            从要交换的位置 base = nums[i] 往前推一个，如果比其大，则说明应该排在 base 的后面，
            向右移动一个，nums[j + 1] = nums[j]，腾出位子
            然后再往前推一个，如此往复，直到 某一个值 nums[j] <= base 或者 j < 0 （退出while循环）
            则说明找到了合适的位置（j + 1）
            */
            while (j >= 0 && nums[j] > base) {
                nums[j + 1] = nums[j];  // 1. 将 nums[j] 向右移动一位
                j--;
            }
            nums[j + 1] = base;         // 2. 将 base 赋值到正确位置
        }
    }

    /* 元素交换 */
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /* 哨兵划分 */
    public static int partition(int[] nums, int left, int right) {
        // 以 nums[left] 作为基准数
        int base = nums[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= base)
                j--;          // 从右向左找首个小于基准数的元素
            while (i < j && nums[i] <= base)
                i++;          // 从左向右找首个大于基准数的元素
            swap(nums, i, j); // 交换这两个元素
        }
        swap(nums, i, left);  // 将基准数交换至两子数组的分界线
        return i;             // 返回基准数的索引
    }

    /* 快速排序 */
    public static void quickSort(int[] nums, int left, int right) {
        // 子数组长度为 1 时终止递归
        if (left >= right)
            return;
        // 哨兵划分
        int pivot = partition(nums, left, right);
        // 递归左子数组、右子数组
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    /**
     * 合并左子数组和右子数组
     * 左子数组区间 [left, mid]
     * 右子数组区间 [mid + 1, right]
     */
    public static void merge(int[] nums, int left, int mid, int right) {
        // 初始化辅助数组（即要归并的两个数组的拼接：[left, mid] + [mid + 1, right]）
        int[] tmp = Arrays.copyOfRange(nums, left, right + 1);

        // 左子数组的起始索引和结束索引
        int leftStart = 0, leftEnd = mid - left;

        // 右子数组的起始索引和结束索引
        int rightStart = mid + 1 - left, rightEnd = right - left;

        // i, j 分别指向左子数组、右子数组的首元素（相对于辅助数组来说的）
        int i = leftStart, j = rightStart, k = left;

        while (i <= leftEnd && j <= rightEnd) {
            if (tmp[i] <= tmp[j]) {
                nums[k++] = tmp[i++];
            } else {
                nums[k++] = tmp[j++];
            }
        }
        while (i <= leftEnd) {
            nums[k++] = tmp[i++];
        }
        while (j <= rightEnd) {
            nums[k++] = tmp[j++];
        }

//        // 通过覆盖原数组 nums 来合并左子数组和右子数组
//        for (int k = left; k <= right; k++) {
//            // 若“左子数组已全部合并完”，则选取右子数组元素，并且 j++
//            if (i > leftEnd)
//                nums[k] = tmp[j++];
//                // 否则，若“右子数组已全部合并完”或“左子数组元素 < 右子数组元素”，则选取左子数组元素，并且 i++
//            else if (j > rightEnd || tmp[i] <= tmp[j])
//                nums[k] = tmp[i++];
//                // 否则，若“左子数组元素 > 右子数组元素”，则选取右子数组元素，并且 j++
//            else
//                nums[k] = tmp[j++];
//        }
    }

    /* 归并排序 */
    public static void mergeSort(int[] nums, int left, int right) {
        // 终止条件
        if (left >= right) return;       // 当子数组长度为 1 时终止递归
        // 递归划分
        int mid = (left + right) / 2;    // 计算数组中点
        mergeSort(nums, left, mid);      // 递归左子数组
        mergeSort(nums, mid + 1, right); // 递归右子数组
        // 回溯合并
        merge(nums, left, mid, right);
    }


}
