package com.hot100;

/**
 * @Description: 汉明距离  https://leetcode.cn/problems/hamming-distance/
 * @author: li
 * @create: 2022-09-01 13:12
 */
public class N461_HammingDistance {


    /**
     * 3.0 上个方法是异或了后，再去循环求1的个数
     *
     * 看题解找到有个bitCount方法
     * Returns the number of one-bits in the two's complement binary representation of the specified int value.
     * 可以返回1的个数。。。。
     * @author: Li
     * @dateTime: 2022/9/1 13:54
     */
    public int hammingDistance3(int x, int y) {
      return Integer.bitCount(x^y);
    }




    /**
     * 拉跨了，上个方法想的是取二进制，一一比较
     * 结果一看题解，突然明白
     * 要求的是二进制中，不同位的个数，直接异或就行了啊。
     * 异或后，统计1的个数
     * @author: Li
     * @dateTime: 2022/9/1 13:47
     */
    public int hammingDistance2(int x, int y) {
        int z= x ^ y;
        int res=0;

        //不断判断z的第一位，然后z无符号右移，直至为0
        while (z!=0){
            res+=z&1;
            z>>=1;
        }
        return res;
    }




    /**
     * 思路：转为二进制，每一位进行比较，不同，结果++
     * @author: Li
     * @dateTime: 2022/9/1 13:13
     * @return
     */
    public int hammingDistance(int x, int y) {

        //将int转为32位2进制数，字符串，其余补0
        StringBuffer str1 = new StringBuffer();
        for (int i = 31; i >= 0; i--) {
          str1.append((x&(1<<i))==0?"0":"1");
        }

        StringBuffer str2 = new StringBuffer();
        for (int i =31;i>=0;i--){
            str2.append((y&(1<<i))==0?"0":"1");
        }

        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (str1.charAt(i)!=str2.charAt(i)){
                res++;
            }
        }

      return res;
    }


    public static void main(String[] args) {
        N461_HammingDistance test = new N461_HammingDistance();
        System.out.println(test.hammingDistance3(3, 1));

    }
}
