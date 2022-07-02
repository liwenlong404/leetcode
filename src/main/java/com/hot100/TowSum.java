package com.hot100;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Description: 两数之和
 * <p>
 * 给定一个整数数组 num和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target的那两个整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * @author: li
 * @create: 2022-07-02 20:51
 */
public class TowSum {

    //暴力解，双重for循环
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    //hash法
    public static int[] hashTwoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{i, hashMap.get(target - nums[i])};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];
    }

    //对数器
    public static void main(String[] args) {
        int maxSize = 20;
        int maxValue = 20;
        System.out.println("test begin");
        boolean succeed = true;
        System.out.println("test begin");


        int[] arr = generateRandomArray(maxSize, maxValue);
        int target = generateTarget(arr);
        int[] twoSum = twoSum(arr, target);
        int[] hashTwoSum = hashTwoSum(arr, target);


        System.out.println("arr:" + Arrays.toString(arr));
        System.out.println("target" + target);
        System.out.println("twoSum" + Arrays.toString(twoSum));
        System.out.println("hashTwoSum" + Arrays.toString(hashTwoSum));



        System.out.println("test end");


    }


    // 生成随机数组（用于测试）
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    //随机取2数，创建一个target
    public static int generateTarget(int[] arr) {
        int one = (int) ((arr.length) * Math.random());
        int two = (int) ((arr.length) * Math.random());
        if (one == two) {
            two = (int) ((arr.length) * Math.random());
        }

        return arr[one] + arr[two];

    }


}
