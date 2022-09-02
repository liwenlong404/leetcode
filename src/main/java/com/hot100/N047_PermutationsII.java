package com.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 全排列II  https://leetcode.cn/problems/permutations-ii/
 * @author: li
 * @create: 2022-09-02 19:04
 */
public class N047_PermutationsII {


    List<List<Integer>> res = new ArrayList<>();
    ArrayList<Integer> track = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {

        Arrays.sort(nums);
        used = new boolean[nums.length];
        backTrack(nums);
        return res;
    }

    public void backTrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }


        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            //!used[i-1]  因为元素相同，如果要避免重复，我们只需要，让相同元素在排列中的相对位置保持不变
            //!used[i-1] 代码即：第i个元素，只有i-1被选择，i才会选择。同理i被选择，i后面的才会被选择
            //即固定了相对位置，所以其不会重复。
            //使用used[i-1]其实可以正常使用，因为其也是固定了相对位置。
            // 但因为剪枝逻辑的问题，效率会变低
            if (i > 0 && nums[i] == nums[i - 1]&&!used[i-1]) {
                continue;
            }
            track.add(nums[i]);
            used[i] = true;

            backTrack(nums);

            track.remove(track.size()-1);
            used[i]=false;
        }
    }

    public static void main(String[] args) {
        N047_PermutationsII test = new N047_PermutationsII();
        List<List<Integer>> res = test.permuteUnique(new int[]{1, 1, 2});
        for (int i = 0; i < res.size(); i++) {
            List<Integer> integers = res.get(i);
            for (int j = 0; j < integers.size(); j++) {
                System.out.print(integers.get(j));
            }
            System.out.println();
        }
    }

}
