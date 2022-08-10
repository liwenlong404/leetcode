package com.hot100;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @Description: 两数相加 https://leetcode.cn/problems/add-two-numbers/
 * @author: li
 * @create: 2022-08-09 20:12
 */
public class N002_AddTwoNum {

    public static void main(String[] args) {


        ListNode l1 = new ListNode(3);
        ListNode listNode1 = new ListNode(7);
/*        ListNode listNode2 = new ListNode(9);
        ListNode listNode3 = new ListNode(9);*/


        l1.next = listNode1;
/*        listNode1.next = listNode2;
        listNode2.next = listNode3;*/

        ListNode l2 = new ListNode(9);
        ListNode istNode1 = new ListNode(2);
/*        ListNode istNode2 = new ListNode(9);
        ListNode istNode3 = new ListNode(9);
        ListNode istNode4 = new ListNode(9);
        ListNode istNode5 = new ListNode(9);
        ListNode istNode6 = new ListNode(9);*/
       // ListNode istNode7 = new ListNode(9);
       // ListNode istNode8 = new ListNode(9);
        //ListNode istNode9 = new ListNode(9);


        l2.next = istNode1;
/*        istNode1.next = istNode2;
        istNode2.next = istNode3;
        istNode3.next = istNode4;
        istNode4.next = istNode5;
        istNode5.next = istNode6;*/
        //istNode6.next = istNode7;
        //istNode7.next = istNode8;
        //istNode8.next = istNode9;

        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }


    /**
     * 优化改进第二版
     * @author: Li
     * @dateTime: 2022/8/9 23:13
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;

        //over 为进位数。
        int over=0;
        while (l1!=null||l2!=null){
            //后序如果有一方没了，用 0 填充，继续循环计算
            int num1 = l1==null?0:l1.val;
            int num2 = l2==null?0:l2.val;

            //将进位数，和num1、num2一起计算。
            int sum = num1 + num2+over;
            //计算下一次的进位数
            over = sum / 10;
            //留下的数  因为是取余，没满10，仍然是二者和，满了10，就是多出来需要留下的
            sum=sum%10;

            cur.next=new ListNode(sum);
            cur=cur.next;

            //因为可能有一方较短，会提前结束，所以判断一下
            if (l1!=null){
                l1=l1.next;
            }
            if (l2!=null){
                l2=l2.next;
            }
        }
        //因为每次over得到的是下一次的进位信息
        //如果上面2个链表都结束了，但仍然需要判断上面末位时，产生的进位
        if(over==1){
            cur.next=new ListNode(over);
        }

        return res.next;
    }


    /**
     *
     * 第一版
     * @author: Li
     * @dateTime: 2022/8/9 23:11
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode tmp = res;

        while (l1 != null && l2 != null) {
            int l1num = l1.val;
            int l2num = l2.val;
            int nextnum = 0;

            if (res.val +l1num + l2num > 20) {
                res.val += (l1num + l2num) % 20;
                nextnum = 2;
            }

            else if (res.val +l1num + l2num >= 10) {
                if (res.val==0) {
                    res.val += (res.val + l1num + l2num) % 10;
                    nextnum = 1;
                }else {
                    res.val = (res.val + l1num + l2num) % 10;
                    nextnum = 1;
                }

            }
            else if (res.val +l1num + l2num < 10) {
                res.val += (l1num + l2num);
            }

            if (l1.next == null && l2.next== null&&nextnum==0){
                return tmp;
            }

            res.next = new ListNode();
            res = res.next;
            res.val += nextnum;
            l1 = l1.next;
            l2 = l2.next;
        }
        //如果一方提前结束
        while (l1 != null) {
            int nextnum = 0;
            if (res.val + l1.val > 20) {
                res.val = (res.val + l1.val) % 20;
                nextnum = 2;
            }

            else if (res.val + l1.val >= 10) {
                res.val = (res.val + l1.val) % 10;
                nextnum = 1;
            }
            else if (res.val + l1.val < 10) {
                res.val = res.val + l1.val;
            }
            if (l1.next == null && nextnum==0){
                break;
            }

            res.next = new ListNode();
            res = res.next;
            res.val += nextnum;
            l1 = l1.next;
        }

        while (l2 != null) {
            int nextnum = 0;
            if (res.val + l2.val > 20) {
                res.val = (res.val + l2.val) % 20;
                nextnum = 2;
            }
            else if (res.val + l2.val >= 10) {
                res.val = (res.val + l2.val) % 10;
                nextnum = 1;
            }
            else if (res.val + l2.val < 10) {
                res.val = res.val + l2.val;
            }

            if (l2.next == null &&nextnum==0){
                break;
            }

            res.next = new ListNode();
            res = res.next;
            res.val += nextnum;
            l2 = l2.next;
        }

        return tmp;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}