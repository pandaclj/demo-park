package com.panda.demo.common.redpackage;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目
 * 100元红包，分给10个人，要求最小1分，最大为红包总数的90%
 * <p>
 * <p>
 * 解题思路
 * 先随机，再逐个调整
 *
 * @author huixiangdou
 * @date 2020/4/29
 */
public class RedPackage {

    public static List<Integer> split(int totalMoney, int num, int min) {
        Map<Integer, Integer> map = new HashMap<>(num);
        //计算最大值
        int max = (int) (totalMoney * 0.9);
        //剩余金额
        int availableMoney = totalMoney;

        //先随机num个数，最大为max，最小为min
        //最后剩余金额存在三种情况，1：还有剩余，2：刚好，3：不够，余额扣成负数
        for (int i = 0; i < num; i++) {
            int v = (int) (Math.random() * max);
            if (v == 0) {
                v = min;
            }
            availableMoney -= v;
            map.put(i, v);
        }

        //还有剩余后者不够,需要调整部分红包金额，使之刚好
        while (availableMoney != 0) {
            //遍历
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (availableMoney == 0) {
                    //刚好，则直接跳出循环
                    break;
                }

                int v = entry.getValue().intValue();
                //如果有剩余且当前金额为最大，则直接跳过，不用调整
                if (availableMoney > 0 && v == max) {
                    continue;
                }

                //如果没有剩余且当前金额为最小，则直接跳过，不用调整
                if (availableMoney < 0 && v == 1) {
                    continue;
                }

                //计算调整值的范围，使它不会超过max，不会小于min
                //如果有剩余，则调整范围为max-v，调整时做加法
                //如果有溢出，则调整范围为v-1，调整时做减法
                int value = availableMoney > 0 ? (max - v) : (v - 1);
                int adjustV = (int) (Math.random() * value);


                if (availableMoney > 0) { //如果有剩余且剩余的金额大于需要调整的值
                    if (availableMoney > adjustV) { //如果剩余金额大于调整值，则此次调整为adjustV，剩余金额=剩余金额-adjustV
                        availableMoney = availableMoney - adjustV;
                    } else {
                        adjustV = availableMoney; //如果剩余金额小于调整值，则此次调整为adjust，剩余金额为0
                        availableMoney = 0;
                    }
                } else { //如果溢出金额大于调整值
                    if (-availableMoney > adjustV) {
                        availableMoney = availableMoney + adjustV;
                    } else {
                        adjustV = -availableMoney;
                        availableMoney = 0;
                    }
                    adjustV = -adjustV;
                }

                v = v + adjustV;
                map.put(entry.getKey(), v);
            }
        }

        List<Integer> list = new ArrayList<>(num);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = RedPackage.split(100, 10, 1);
        System.out.println(JSON.toJSONString(list));

        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println(sum);
    }
}
