package com.hot100;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 全排列  https://leetcode.cn/problems/permutations/
 * @author: li
 * @create: 2022-09-01 15:17
 */
public class N046_Permutations {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        //记录路径
        LinkedList<Integer> track = new LinkedList<>();

        // [路径] 中的元素会被标记为 true，避免重复使用
        boolean[] used = new boolean[nums.length];

        process(nums, track, used);

        return res;
    }

    /**
     * 路径：记录在track中
     * 选择列表：nums中不存在于track的那些元素（user[i]=false）
     * 结束条件：nums中的元素全在track中出现
     *
     * @author: Li
     * @dateTime: 2022/9/1 15:18
     */
    public void process(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // base case 触发结束条件
        if (track.size() == nums.length) {
            //将路径加入结果
            res.add(new LinkedList(track));
            return;
        }

        //遍历数组中的所有元素，通过user数组判断是否已经使用
        for (int i = 0; i < nums.length; i++) {
            //如果此元素已经用过，跳过
            if (used[i]) {
                continue;
            }

            //没用过，做出选择
            track.add(nums[i]);
            used[i]=true;

            //进入下一层决策树
            process(nums, track, used);

            //回溯上一次决策
            track.removeLast();
            used[i]=false;

        }

    }

    public static void main(String[] args) {
        N046_Permutations test = new N046_Permutations();
        List<List<Integer>> res = test.permute(new int[]{1, 2, 3});
        for (int i = 0; i < res.size(); i++) {
            List<Integer> integers = res.get(i);
            for (int j = 0; j < integers.size(); j++) {
                System.out.print(integers.get(j));
            }
            System.out.println();
        }
    }

}
