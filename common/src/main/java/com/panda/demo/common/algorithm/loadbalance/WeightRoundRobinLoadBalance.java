package com.panda.demo.common.algorithm.loadbalance;

import com.google.common.collect.Lists;

import java.math.BigInteger;
import java.util.List;

/**
 * 加权轮询
 *
 * @author huixiangdou
 * @date 2019-03-19
 */
public class WeightRoundRobinLoadBalance extends AbstractLoadBalance {
    /**
     * 当前权重值
     */
    private int currentWeight;
    /**
     * 最大权重值
     */
    private int maxWeight;
    /**
     * 权重值最大公约数
     */
    private int gcdWeight;
    private int index = -1;

    @Override
    public Server doSelect(List<Server> servers) {
        maxWeight = max(servers);
        gcdWeight = gcd(servers);

        while (true) {
            if (currentWeight == 0) {
                currentWeight = maxWeight;
            }
            index++;
            if (index >= servers.size()) {
                currentWeight -= gcdWeight;
                index = 0;
            }
            if (servers.get(index).getWeight() >= currentWeight) {
                return servers.get(index);
            }
        }
    }

    //求最大权重值
    private int max(List<Server> servers) {
        int max = servers.get(0).getWeight();
        for (int i = 1; i < servers.size(); i++) {
            int tmp = servers.get(i).getWeight();
            if (tmp > max) {
                max = tmp;
            }
        }
        return max;
    }

    //求最大公约数
    private int gcd(List<Server> servers) {
        int gcd = servers.get(0).getWeight();
        for (int i = 1; i < servers.size(); i++) {
            gcd = new BigInteger(String.valueOf(gcd)).gcd(new BigInteger(String.valueOf(servers.get(i).getWeight()))).intValue();
        }
        return gcd;
    }

    public static void main(String[] args) {
        WeightRoundRobinLoadBalance loadBalance = new WeightRoundRobinLoadBalance();
        List<Server> servers = Lists.newArrayList(new Server("a", 4), new Server("b", 2), new Server("c", 1));
        for (int i = 0; i < 14; i++) {
            System.out.println(loadBalance.select(servers).getIp());
        }
    }
}
