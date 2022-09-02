package com.hot100;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 组合 https://leetcode.cn/problems/combinations/
 * @author: li
 * @create: 2022-09-02 18:14
 */
public class N077_Combinations {

    List<List<Integer>> res=new ArrayList<>();
    ArrayList<Integer> track=new ArrayList<>();


    /**
     * 1到N，任意K个数
     * @author: Li
     * @dateTime: 2022/9/2 18:15
     */
    public List<List<Integer>> combine(int n, int k) {

        backTrack(n, k,1);

        return res;
    }


    /**
     * n,k为固定参数
     *
     * 其实这道题和N078子集问题差不多，只不过加了个只要数的第二层结果的base case
     *
     * @author: Li
     * @dateTime: 2022/9/2 18:23
     */
    public void backTrack(int n,int k,int start){
        //base case
        if (track.size()==k){
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = start; i <=n; i++) {
            //选择
            track.add(i);

            backTrack(n,k,i+1);

            //回溯
            track.remove(track.size()-1);

        }
    }

    public static void main(String[] args) {

    }
}
