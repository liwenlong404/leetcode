package com.exercise.sort;

import java.util.Arrays;

/**
 * @Description: 快排
 * 时间复杂度：O(N* logN)   空间复杂度：O(logN)     稳定性：无
 * @author: li
 * @create: 2022-07-26 12:20
 */
public class QuickSort {

    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
        int[] arr1 = generateRandomArray(10, maxValue);
        quickSort2(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    /**
     * 1.0 partition
     * 对于数组最后一个值x, <=x的整体放在左边, >x的整体放在右边
     * 返回int，为排序后 左区右边界 的index
     * <p>
     * 这个是 0,1  的 partition 排序过程。
     *
     * @author: Li
     * @dateTime: 2022/7/27 17:07
     */
    public static int partition(int[] arr, int L, int R) {
        //边界条件
        if (L > R) {
            return -1;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index++, ++lessEqual);
            }else {
                index++;
            }

        }

        //因为没判断最后一个R，所以需要最后交换一次
        swap(arr, ++lessEqual, R);

        return lessEqual;
    }


    /**
     * 2.0的partition，也是所谓的荷兰国旗问题
     * <p>
     * 给一个数组arr，以数组最后一个值num做划分
     * 将数组中小于num的数放在左边，等于num的数放在中间，大于num的数全部放在右边。
     * <p>
     * 相当于
     * <p>
     * 返回等于区域
     *
     * @author: Li
     * @dateTime: 2022/7/26 12:25
     */
    public static int[] partition2(int[] arr, int L, int R) {
        //边界条件，无效的范围
        if (L > R) {
            return new int[]{-1, -1};
        }

        //边界条件  一个数
        if (L == R) {
            return new int[]{L, R};
        }
        //左区的右边界
        int less = L - 1;
        //右区的左边界
        int more = R;
        //当前位置
        int index = L;
        //当前位置不能撞上右区
        while (index < more) {
            //相等的放中间，index++，左区，右区不动
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                //小于，将当前数与左区边界数，交换，然后左区++,index++
                swap(arr, index++, ++less);
            } else {
                //大于，将当前数与右区边界数，交换，右区左移--，index++
                swap(arr, index, --more);
            }
        }
        //返回等于区，后面用时需要-1  +1，如果返回是左区边界，右区边界则不用+1，-1
        // swap(arr, R, more++);
        // return new int[]{less,more};
        swap(arr, R, more);
        return new int[]{less+1,more};
    }


    /**
     * 快排1.0
     *
     * @param arr 需要排序的数组
     * @author: Li
     * @dateTime: 2022/7/26 13:20
     */
    public static void quickSort1(int[] arr) {
        //边界条件
        if (arr == null || arr.length < 2) {
            return;
        }

        //对进来的数组调用process方法进行排序
        process1(arr, 0, arr.length - 1);
    }

    /**
     * 对arr数组中 L 到 R 的范围 partition排序
     *
     * @author: Li
     * @dateTime: 2022/7/27 17:04
     */
    public static void process1(int[] arr, int L, int R) {
        //边界条件，L，R
        if (L >= R) {
            return;
        }

        //L到R partition排序  mid为左区右边界
        int mid = partition(arr, L, R);
        process1(arr, L, mid - 1);
        process1(arr, mid+1, R);
    }

    public static void quickSort2(int[] arr) {
        //边界条件
        if (arr == null || arr.length < 2) {
            return;
        }

        //对进来的数组调用process方法进行排序
        process2(arr, 0, arr.length - 1);
    }

    /**
     *
     * 跟上面的sort比，主要变化是partition方法的改变。
     * 1.0的partition是0  1 的partition
     * 2.0的partition是3则的一个判断（<放左  =放中    >放右 ）
     *
     *
     * @author: Li
     * @dateTime: 2022/7/27 17:04
     */
    public static void process2(int[] arr, int L, int R) {
        //边界条件，L，R
        if (L >= R) {
            return;
        }

        //L到R partition排序  mid为左区右边界
        int[] area = partition2(arr, L, R);


        //因为partition返回的是等于区，所以需要+1  -1，如果返回是左区边界，右区边界则不用+1，-1
        // process2(arr, L,  area[0]);
        // process2(arr, area[1], R);
        process2(arr, L,  area[0]-1);
        process2(arr, area[1]+1, R);
    }


    /**
     * 交换方法，将int[]数组的，i位置和j位置交换
     *
     * @author: Li
     * @dateTime: 2022/7/26 12:29
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
   /* public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            // quickSort2(arr2);
            // quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }*/

}
