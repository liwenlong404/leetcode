package com.hot100;

import sun.text.resources.cldr.ti.FormatData_ti;

import javax.xml.validation.ValidatorHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: N皇后  https://leetcode.cn/problems/n-queens/
 * @author: li
 * @create: 2022-09-01 16:19
 */
public class N051_NQueens {


    List<List<String>> res=new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) {
            return null;
        }
        int[] record = new int[n];
        process(0, record, n);
        return res;
    }


    /**
     * 因为是用数组记录的每个皇后存放的位置，但题目要求用List<String>保存结果
     * 所以对int[] ，进行填充字符，转换
     * @author: Li
     * @dateTime: 2022/9/1 17:23
     */
    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }


    /**
     * i为行，record为记录，n为固定参数（n皇后）
     * @author: Li
     * @dateTime: 2022/9/1 17:28
     */
    public void process(int i, int[] record, int n) {
        //如果最后一行放下了，加入res
        if (i == n) {
            res.add(generateBoard(record.clone(),n));
        }
        // 遍历每一列，位置正确，放入第 i 行的皇后
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                process(i + 1, record, n);
            }
        }
    }

    /**
     * 判断是否冲突
     * 因为皇后不可能在行上冲突，所以我们只需要判断是否在列上，斜线上冲突。
     * 斜线判断：例如 a在3行，6列   b在5行，8列。那么 |3-5|==|6-8|。冲突
     *
     * @author: Li
     * @dateTime: 2022/9/1 16:36
     */
    public  boolean isValid(int[] record, int i, int j) {
        // 0..i-1
        //判断 i 行，之前的皇后，是否在列上与 j 冲突。
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


}
