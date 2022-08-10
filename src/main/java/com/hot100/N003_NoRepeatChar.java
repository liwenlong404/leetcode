package com.hot100;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 3.无重复字符的最长子串  https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * @author: li
 * @create: 2022-08-10 12:41
 */
public class N003_NoRepeatChar {



    /**
     * 3.0  相比2.0，减少了对 0个和1个的if判断
     * @author: Li
     * @dateTime: 2022/8/10 14:20
     */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    /**
     * 优化2.0 时间复杂度O(N)
     *
     * @author: Li
     * @dateTime: 2022/8/10 14:15
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        //代表每个子串的终点，也是起点，不回退
        //如果  0...rk  为不重复子串，那么 1.....rk也肯定是不重复子串。
        //所以当要计算从 2 开始的子串时，可以直接从 rk开始判断
        //还可以通过rk  - i 得到长度
        int rk = 0;
        int res = 0;
        HashSet set = new HashSet();
        for (int i = 0; i < s.length() - 1; i++) {
            while (rk <= s.length() - 1 && !set.contains(s.charAt(rk))) {
                set.add(s.charAt(rk));
                rk++;

            }
            if (i!=0) {
                set.remove(s.charAt(i));
            }
            res = Math.max(res, rk - i);

        }

        return res;
    }


    /**
     * 方法1 双重for暴力解，但多了许多不必要的数据结构
     *
     * @author: Li
     * @dateTime: 2022/8/10 13:28
     */
    public static int lengthOfLongestSubstring1(String s) {
        //暴力解，双重for循环
        char[] str = s.toCharArray();
        if (str.length == 0) {
            return 0;
        }
        if (str.length == 1) {
            return 1;
        }
        ArrayList<Character> res = new ArrayList<>();
        for (int i = 0; i < str.length - 1; i++) {
            ArrayList<Character> tmp = new ArrayList<>();
            for (int j = i; j <= str.length - 1; j++) {

                tmp.add(str[j]);
                if (j == str.length - 1 || tmp.contains(str[j + 1])) {
                    break;
                }
            }
            if (tmp.size() > res.size()) {
                res.removeAll(res);
                res.addAll(tmp);
            }

        }

        return res.size();
    }


    public static void main(String[] args) {
        int pwwkew = lengthOfLongestSubstring("sbaksd");
        System.out.println(pwwkew);
    }
}
