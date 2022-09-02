package com.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 子集  https://leetcode.cn/problems/subsets/
 * @author: li
 * @create: 2022-09-01 14:46
 */
public class N078_Subsets {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    /**
     *
     * @author: Li
     * @dateTime: 2022/9/1 14:47
     */
    public List<List<Integer>> subsets(int[] nums) {

        process(nums, 0);

        return res;
    }

    public void process(int[] nums,int start){
        //前序位置，每个节点的值都是一个子集
        res.add(new LinkedList<>(track));


        for (int i = start; i < nums.length; i++) {

            //选择
            track.addLast(nums[i]);

            //通过start控制树枝的遍历
            process(nums, i+1);
            //撤销选择
            track.removeLast();
        }

    }


    public static void main(String[] args) {
        int[] ints = {1, 2, 3};
        N078_Subsets test = new N078_Subsets();
        List<List<Integer>> res = test.subsets(ints);
        for (int i = 0; i < res.size(); i++) {
            List<Integer> tmp = res.get(i);
            for (int j = 0; j < tmp.size(); j++) {
                System.out.print(tmp.get(j));
            }
            System.out.println();
        }
    }

}
