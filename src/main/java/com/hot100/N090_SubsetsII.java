package com.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 子集II  https://leetcode.cn/problems/subsets-ii/
 * @author: li
 * @create: 2022-09-02 18:48
 */
public class N090_SubsetsII {

    List<List<Integer>> res=new ArrayList<>();
    ArrayList<Integer> track=new ArrayList<>();



    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);
        backTrack(nums, 0);


        return res;
    }

    /**
     * 和原子集问题稍微不同，集合有重负元素，但要求不能有同样结果。所以在对树的遍历加值的时候，需要进行判断
     * @author: Li
     * @dateTime: 2022/9/2 18:50
     */
    public void backTrack(int[] nums,int start){

        res.add(new ArrayList<>(track));

        for (int i = start; i < nums.length; i++) {
            //如果当前数与上一个数相同
            if (i>start&&nums[i]==nums[i-1]){
                continue;
            }

            track.add(nums[i]);

            backTrack(nums, i+1);

            track.remove(track.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 2};
        N090_SubsetsII test = new N090_SubsetsII();
        List<List<Integer>> res = test.subsetsWithDup(ints);
        for (int i = 0; i < res.size(); i++) {
            List<Integer> tmp = res.get(i);
            for (int j = 0; j < tmp.size(); j++) {
                System.out.print(tmp.get(j));
            }
            System.out.println();
        }
    }
}
