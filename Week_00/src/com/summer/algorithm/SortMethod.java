package com.summer.algorithm;

/**
 * @author hongzhengwei
 * @create 2021/3/1 9:46 上午
 * @desc 排序合集
 **/
public class SortMethod {

    public  void main(String[] args) {
        SortMethod sortMethod = new SortMethod();
        int[] nums = new int[]{7,11,7, 4, 2, 11,5, 3};
//        int[] sort = sortMethod.selectionSort(nums, nums.length);
        int[] a = new int[]{1, 3, 5, 7};
        int[] b = new int[]{2, 4, 6, 8, 212, 224};
        sortMethod.mergeNums(a, b);
        sortMethod.quickSort(nums, 0,nums.length-1);


    }

    /**
     * @author hongzhengwei
     * @create 2021/3/1 9:58 上午
     * @desc 冒泡排序
     * 冒泡的过程只涉及相邻数据的交换操作，只需要常量级的临时空间，所以它的空间复杂度为 O(1)，是一个原地排序算法。
     * 在冒泡排序中，只有交换才可以改变两个元素的前后顺序，是稳定的算法
     * 最好情况下，要排序的数据已经是有序的了，我们只需要进行一次冒泡操作，就可以结束了，所以最好情况时间复杂度是 O(n)。而最坏的情况是，要排序的数据刚好是倒序排列的，我们需要进行 n 次冒泡操作，所以最坏情况时间复杂度为 O(n2)。
     **/
    public int[] bubbleSort(int[] nums, int size) {
        if (size > 1) {
            for (int i = 0; i < size; i++) {
                boolean sortedFlag = true;
                for (int j = 0; j < size - i - 1; j++) {
                    if (nums[j] < nums[j + 1]) {
                        int buff;
                        buff = nums[j];
                        nums[j] = nums[j + 1];
                        nums[j + 1] = buff;
                        sortedFlag = false;
                    }
                }
                if (sortedFlag) {
                    break;
                }
            }
        }
        return nums;
    }


    /**
     * @author hongzhengwei
     * @create 2021/3/1 10:04 上午
     * @desc 插入排序
     * 插入排序算法的运行并不需要额外的存储空间，所以空间复杂度是 O(1)，也就是说，这是一个原地排序算法。
     * 在插入排序中，对于值相同的元素，我们可以选择将后面出现的元素，插入到前面出现元素的后面，这样就可以保持原有的前后顺序不变，所以插入排序是稳定的排序算法。
     * 每次插入操作都相当于在数组中插入一个数据，循环执行 n 次插入操作，所以平均时间复杂度为 O(n2)。
     **/
    public int[] insertionSort(int[] nums, int size) {
        if (size <= 1) {
            return nums;
        }
        for (int i = 1; i < size; i++) {
            //记录当前要插入的值
            int value = nums[i];
            //对已有数据进行比较大小
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > value) {
                    //若是比要插入的值大，则往后移动一位
                    nums[j + 1] = nums[j];
                } else {
                    //插入数值
                    nums[j + 1] = value;
                    break;
                }
            }
        }
        return nums;
    }


    /**
     * @author hongzhengwei
     * @create 2021/3/1 10:47 上午
     * @desc 选择排序
     * 选择排序空间复杂度为 O(1)，是一种原地排序算法
     * 选择排序空间复杂度为 O(1)，是一种原地排序算法。选择排序的最好情况时间复杂度、最坏情况和平均情况时间复杂度都为 O(n2)。
     * 选择排序每次都要找剩余未排序元素中的最小值，并和前面的元素交换位置，这样破坏了稳定性。
     *
     **/
    public int[] selectionSort(int[] nums, int size) {
        if (size <= 1) {
            return nums;
        }
        for (int i = 0; i < size; i++) {
            //记录未排序区最小的值
            int minValue = nums[i];
            int minIndex = i;
            //对已有数据进行比较大小
            for (int j = i; j < size; j++) {
                if (nums[j] < minValue) {
                    //比较出最小的值
                    minValue = nums[j];
                    minIndex = j;
                }
            }
            int temp;
            temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
        return nums;
    }

    /**
     * @author hongzhengwei
     * @create 2021/3/1 11:02 上午
     * @desc 归并排序
     **/
    public int[] mergeSort(int[] nums, int size) {
        if (size <= 1) {
            return nums;
        }
        mergeSort(nums, 0, size - 1);
        return nums;
    }

    /**
     * @author hongzhengwei
     * @create 2021/3/1 11:06 上午
     * @desc 归并排序递归算法
     *
     * 递推公式：merge_sort(p…r) = merge(merge_sort(p…q), merge_sort(q+1…r))终止条件：p >= r 不用再继续分解
        T(1) = C；   n=1时，只需要常量级的执行时间，所以表示为C。
        T(n) = 2*T(n/2) + n； n>1
             = 2^k * T(n/2^k) + k * n
     **/
    private int[] mergeSort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return nums;
        }
        int half = (head + tail) / 2;
        int[] heads = mergeSort(nums, head, half);
        int[] tails = mergeSort(nums, half + 1, tail);
        return mergeNums(heads, tails);
    }

    private int[] mergeNums(int[] heads, int[] tails) {
        int[] nums = new int[heads.length + tails.length];
        int headIndex = 0;
        int tailIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (headIndex < heads.length && tailIndex < tails.length) {
                if (heads[headIndex] > tails[tailIndex]) {
                    nums[i] = tails[tailIndex];
                    tailIndex++;
                } else {
                    nums[i] = heads[headIndex];
                    headIndex++;
                }
            } else if (tailIndex < tails.length) {
                nums[i] = tails[tailIndex];
                tailIndex++;
            } else if (headIndex < heads.length) {
                nums[i] = heads[headIndex];
                headIndex++;
            }


        }
        return nums;
    }


    /**
     * @author hongzhengwei
     * @create 2021/3/1 11:06 上午
     * @desc 快速排序递归算法
     *  递推公式：
     *  quick_sort(p…r) = quick_sort(p…q-1) + quick_sort(q+1… r)
     *  终止条件：p >= r
     *
    T(1) = C；   n=1时，只需要常量级的执行时间，所以表示为C。
    T(n) = 2*T(n/2) + n； n>1
     **/
    private void quickSort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return;
        }
        int half = getMiddle(nums, head, tail);
        quickSort(nums, head, half-1);
        quickSort(nums, half + 1, tail);
    }

    private static int getMiddle(int[] array, int low, int high) {
        int temp = array[low];
        int i = low;
        while (low < high) {
            while (low < high && array[high] >= temp) {
                high--;
            }
            while (low < high && array[low] <= temp) {
                low++;
            }

            if (low < high) {
                int flag = array[high];
                array[high] = array[low];
                array[low] = flag;
            }
        }

        if (low == high) {
            array[i] = array[high];
            array[high] = temp;
        }

        return low;
    }


}
