package com.kilogate.hello.algorithm.core;

import java.lang.Math;
import java.util.Arrays;

/**
 * 常用排序算法
 *
 * @author fengquanwei
 * @create 2017/10/14 13:34
 **/
public class Sort {
    /**
     * 插入排序
     * 在有序小序列的基础上一次插入一个元素，比较从小序列末尾开始。
     * 稳定排序算法，时间复杂度为 O(n^2)，空间复杂度为 O(1)。
     */
    public static void insertSort(int[] datas) {
        if (datas != null) {
            int length = datas.length;
            if (length > 1) {
                int i, j, t;
                for (i = 1; i < length; i++) {
                    if (datas[i] < datas[i - 1]) {
                        t = datas[i];
                        datas[i] = datas[i - 1];
                        for (j = i - 2; j >= 0 && datas[j] > t; j--) {
                            datas[j + 1] = datas[j];
                        }
                        datas[j + 1] = t;
                    }
                }
            }
        }
    }

    /**
     * 冒泡排序
     * 把小的元素往前调或者把大的元素往后调，比较和交换发生在两个相邻的元素之间。
     * 稳定排序算法，时间复杂度为 O(n^2)，空间复杂度为 O(1)。
     */
    public static void bubbleSort(int[] datas) {
        if (datas != null) {
            int length = datas.length;
            if (length > 1) {
                int i, j, t, s = 1;
                for (i = 0; s == 1 && i < length - 1; i++) {
                    s = 0;
                    for (j = 0; j < length - 1; j++) {
                        if (datas[j] > datas[j + 1]) {
                            t = datas[j];
                            datas[j] = datas[j + 1];
                            datas[j + 1] = t;
                            s = 1;
                        }
                    }
                }
            }
        }
    }

    /**
     * 选择排序
     * 给每个位置选择当前最小的元素，交换当前元素与最小元素。
     * 不稳定排序算法，时间复杂度为 O(n^2)，空间复杂度为 O(1)。
     */
    public static void selectSort(int[] datas) {
        if (datas != null) {
            int length = datas.length;
            if (length > 1) {
                int i, j, k, t;
                for (i = 0; i < length - 1; i++) {
                    k = i;
                    for (j = i + 1; j < length; j++) {
                        if (datas[k] > datas[j]) {
                            k = j;
                        }
                    }
                    if (i != k) {
                        t = datas[i];
                        datas[i] = datas[k];
                        datas[k] = t;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序
     * 缩小增量排序，对插入排序的改进。将整个待排记录序列分割成若干子序列分别进行插入排序，待基本有序时，再对全体记录进行一次插入排序。
     * 不稳定的排序方法。
     */
    public static void shellSort(int[] datas) {
        if (datas != null) {
            int length = datas.length;
            if (length > 1) {
                int i, j, k, t, delta, d;

                // 初始化增量序列
                int[] deltas = new int[(int) (Math.log(length) / Math.log(2))];
                delta = length;
                d = 0;
                while ((delta = delta / 2) > 0) {
                    deltas[d] = delta;
                    d++;
                }

                for (d = 0; d < deltas.length; d++) {
                    k = deltas[d];
                    // 插入排序
                    for (i = k; i < length; i += k) {
                        if (datas[i] < datas[i - k]) {
                            t = datas[i];
                            datas[i] = datas[i - k];
                            for (j = i - k; j >= 0 && datas[j] > t; j -= k) {
                                datas[j + k] = datas[j];
                            }
                            datas[j + k] = t;
                        }
                    }
                }
            }
        }
    }

    /**
     * 快速排序
     * 通过一趟排序将待排记录划分为独立的两部分，其中一部分记录均比另一部分记录小，然后再分别对这两部分记录继续进行快速排序，以达到整个序列有序。
     * 不稳定的排序方法，时间复杂度为 O(nlog2n)，空间复杂度为 O(1)。
     */
    public static void quickSort(int[] datas, int low, int high) {
        if (datas != null) {
            int length = datas.length;
            if (length > 1) {
                int i = low, j = high, pivot = datas[low], t;
                while (i < j) {
                    while (i < j && datas[j] >= pivot) {
                        j--;
                    }
                    while (i < j && datas[i] <= pivot) {
                        i++;
                    }
                    if (i < j) {
                        t = datas[i];
                        datas[i] = datas[j];
                        datas[j] = t;
                    }
                }
                if (i == j && datas[i] < pivot) {
                    datas[low] = datas[i];
                    datas[i] = pivot;
                }
                if (low < i - 1) {
                    quickSort(datas, low, i - 1);
                }
                if (i + 1 < high) {
                    quickSort(datas, i + 1, high);
                }
            }
        }

    }

    /**
     * 堆排序
     * 堆：完全二叉树（非终端结点的值均不大于或不小于其左右孩子结点的值）。
     * 对一组待排序记录，首先把它们按堆的定义排成一个序列（建立初始堆），输出堆顶的最小记录（小顶堆）。然后将剩余关键字再调整成新堆，得到次小关键字，如此反复，直到全部记录有序为止。
     * 不稳定的排序方法，时间复杂度为 O(nlog2n)，空间复杂度为 O(1)。
     */
    public static void heapSort(int[] datas) {
        if (datas != null) {
            int length = datas.length;
            if (length > 1) {
                int i, t, last;
                for (i = 0; i < length - 1; i++) {
                    last = length - i - 1;
                    buildMaxHeap(datas, last);
                    t = datas[last];
                    datas[last] = datas[0];
                    datas[0] = t;
                }
            }
        }
    }

    // 构造大根堆
    private static void buildMaxHeap(int[] datas, int last) {
        if (datas != null && last > 1) {
            int length = datas.length;
            if (length > 1) {
                int left, right, bigger, temp;
                for (int i = (last - 1) / 2; i >= 0; i--) {
                    bigger = i;
                    left = i * 2 + 1;
                    right = i * 2 + 2;

                    if (datas[left] > datas[bigger]) {
                        bigger = left;
                    }
                    if (right < last && datas[right] > datas[bigger]) {
                        bigger = right;
                    }

                    if (i != bigger) {
                        temp = datas[i];
                        datas[i] = datas[bigger];
                        datas[bigger] = temp;
                    }
                }
            }
        }
    }

    /**
     * 归并排序
     * 归并是指将两个或两个以上的有序文件合并成为一个新的有序文件。归并排序是把一个有 n 个记录的无序文件看成是由 n 个长度为 1 的有序子文件组成的文件，然后进行两两归并，得到 n/2 个长度为 2 或 1 的有序文件，再两两归并，如此重复，直至最后形成包含 n 个记录的有序文件为止。这种反复将两个有序文件归并成一个有序文件的排序方法称为两路归并排序。
     * 稳定的排序方法，时间复杂度为 O(nlog2n)，空间复杂度为 O(n)。
     */
    public static void mergeSort(int[] datas) {
        if (datas != null) {
            int length = datas.length;
            if (length > 1) {
                for (int gap = 1; gap < length; gap = gap * 2) {

                    int i = 0;

                    for (i = 0; i + 2 * gap - 1 < length; i += 2 * gap) {
                        merge(datas, i, i + gap, i + 2 * gap - 1);
                    }

                    // 如果子序列个数为奇数个，最后一个子序列不参与合并；如果子序列个数为偶数个，最后一个子序列长度可能不足 gap 个
                    if (i + gap < length) { // 子序列个数为偶数个
                        merge(datas, i, i + gap, length - 1); // 合并最后一组
                    }
                }

            }
        }
    }

    // 合并 datas[low, mid] 与 datas[mid+1, high]
    private static void merge(int[] datas, int low, int mid, int high) {
        if (datas != null) {
            int length = datas.length;
            if (length > 1) {
                int size = high - low + 1;
                if (size > 0) {
                    int[] merge = new int[size];

                    int i = low, j = mid, k = 0;

                    while (i < mid && j <= high) {
                        if (datas[i] < datas[j]) {
                            merge[k] = datas[i];
                            i++;
                            k++;
                        } else {
                            merge[k] = datas[j];
                            j++;
                            k++;
                        }
                    }

                    while (i < mid) {
                        merge[k] = datas[i];
                        i++;
                        k++;
                    }

                    while (j <= high) {
                        merge[k] = datas[j];
                        j++;
                        k++;
                    }

                    for (k = 0, i = low; i <= high; k++, i++) {
                        datas[i] = merge[k];
                    }
                }
            }
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] data = new int[]{5, 4, 3, 2, 1};
        mergeSort(data);
        System.out.println(Arrays.toString(data));
    }
}
