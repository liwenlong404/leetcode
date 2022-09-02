package com.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 组合总和 https://leetcode.cn/problems/combination-sum/
 * @author: li
 * @create: 2022-09-02 20:15
 */
public class N039_CombinationSum {


    List<List<Integer>> res = new ArrayList<>();
    ArrayList<Integer> track = new ArrayList<>();
    int sum=0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        backTrack(candidates,target,0);


        return res;
    }


    /**
     * 如果不重复使用元素。在N077的组合问题上，使用的是start+1。
     * 如果我们需要可以使用重复元素，那么直接使用start，不+1。然后写base case
     * @author: Li
     * @dateTime: 2022/9/2 20:35
     */
    public void backTrack(int[] candidates,int target,int start){
        if (sum==target){
            res.add(new ArrayList<>(track));
            return;
        }
        if (sum>target){
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            track.add(candidates[i]);
            sum+=candidates[i];

            backTrack(candidates, target,i);

            track.remove(track.size()-1);
            sum-=candidates[i];
        }
    }
}
