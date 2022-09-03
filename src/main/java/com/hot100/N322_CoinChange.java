package com.hot100;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;

/**
 * @Description: 零钱兑换 https://leetcode.cn/problems/coin-change/
 * @author: li
 * @create: 2022-09-03 16:33
 */
public class N322_CoinChange {




    /**
     * 将递归改为记忆化搜索
     *
     * @author: Li
     * @dateTime: 2022/9/3 19:18
     */


    /*
    备忘录：记录的是每个子问题的最优解。
     */
    int[] meno;

    public int coinChange2(int[] coins, int amount) {
        meno = new int[amount + 1];
        Arrays.fill(meno, -16);
        return dp(coins, amount);

    }

    public int dp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        //先看meno里，是否对此子问题已经求过解。因为初始化为-16.如果值未变，则没进行过求解
        if (meno[amount] != -16) {
            return meno[amount];
        }
        int res = Integer.MAX_VALUE;

        for (int coin : coins) {
            //子问题解
            int subRes = dp(coins, amount - coin);

            //此子问题无解，则跳过。可以理解为在树上向右移动，走其余子问题
            if (subRes == -1) {
                continue;
            }

            //选出最优解
            res = Math.min(res, subRes + 1);
        }
        //因为可能无解，所以做下判断。然后存入备忘录
        meno[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return meno[amount];
    }

    //正确的递归
    public int coinChange1(int[] coins, int amount) {
        //base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            //子问题结果
            int subProblem = coinChange1(coins, amount - coin);

            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, subProblem + 1);
        }

        return res == Integer.MAX_VALUE ? -1 : res;


    }


    ArrayList<Integer> track = new ArrayList<>();
    ArrayList<Integer> res = new ArrayList<>();

    public int coinChange(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }
        backTrack(coins, amount, 0);

        if (res.size() != 0) {
            return Collections.min(res);
        }


        return -1;

    }

    /**
     * 思路1：暴力递归所有组合。经典回溯算法的模板
     * <p>
     * .......超时
     *
     * @author: Li
     * @dateTime: 2022/9/3 16:36
     */
    public void backTrack(int[] coins, int amount, int start) {

        int N = 0;
        for (int i = 0; i < track.size() - 1; i++) {
            N += track.get(i);
        }
        if (N == amount) {
            res.add(track.size() - 1);
            return;
        }

        if (N > amount) {
            return;
        }

        for (int i = start; i < coins.length; i++) {

            track.add(coins[i]);

            backTrack(coins, amount, i);

            track.remove(track.size() - 1);

        }
    }

    public static void main(String[] args) {
        int[] ints = {1, 2,5};
        N322_CoinChange test = new N322_CoinChange();
        System.out.println(test.coinChange2(ints, 11));
    }


}
