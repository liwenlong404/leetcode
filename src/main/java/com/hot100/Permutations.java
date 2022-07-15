package com.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 46.全排列(回溯算法)
 * <p>
 * 给定一个不含重复数字的数组 nums ，
 * 返回其所有可能的全排列。你可以按任意顺序返回答案。
 * @author: li
 * @create: 2022-07-03 21:21
 */
public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        //边界条件 为1
        if (nums.length==1){
            List<Integer> ints =new ArrayList<>();
            ints.add(nums[0]);
            List<List<Integer>> res=new ArrayList<>();
            res.add(ints);
            return res;
        }

       List<List<Integer>> res=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length-1; j++) {
                //两数交换
                int tmp=nums[j];
                nums[j]=nums[j+1];
                nums[j+1]=tmp;

                //每交换一次，记录交换后的数组
                List<Integer> ints =new ArrayList<>();
                for (int num : nums) {
                    ints.add(num);
                }
                res.add(ints);
            }
        }
        return res;

    }


    public static void main(String[] args) {
        int[] nums={5,4,6,2};
        List<List<Integer>> permute = permute(nums);
        for (int i = 0; i < permute.size(); i++) {
            List<Integer> arr = permute.get(i);
            for (int j = 0; j < arr.size(); j++) {
                System.out.print(arr.get(i));
            }
            System.out.println();
        }
    }
}
