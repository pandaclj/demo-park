package com.panda.demo.common.redpackage;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目
 * 比如100元红包，分10个发放，最少一分
 *
 * @author huixiangdou
 * @date 2020/4/29
 */
public class RedPackage2 {

    /**
     * 二倍均值法
     * 正态分布
     *
     *
     * 二倍均值法是每个人分配的金额都在[0 ~ 20]
     *
     * 无上限
     */
    public static List<Integer> split(int totalMoney, int num) {
        int min = 1;

        int remainMoney = totalMoney;
        List<Integer> list = new ArrayList<>(num);

        for (int i = 0; i < num; i++) {
            int max = remainMoney / num * 2;
            int money = (int) (Math.random() * max);
            if (money == 0) {
                money = min;
            }
            list.add(money);

            remainMoney -= money;
            num--;
        }
        return list;
    }


    public static void main(String[] args) {
        List<Integer> list = RedPackage2.split(100, 10);
        System.out.println(JSON.toJSONString(list));

        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println(sum);
    }
}
