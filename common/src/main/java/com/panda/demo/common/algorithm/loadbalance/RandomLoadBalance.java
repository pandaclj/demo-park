package com.panda.demo.common.algorithm.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * 随机
 *
 * @author huixiangdou
 * @date 2019-03-19
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    private Random random = new Random();

    @Override
    public Server doSelect(List<Server> servers) {
        return servers.get(random.nextInt(servers.size()));
    }
}
