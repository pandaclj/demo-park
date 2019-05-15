package com.panda.demo.common.algorithm.loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询
 *
 * @author huixiangdou
 * @date 2019-03-19
 */
public class RoundRobinLoadBalance extends AbstractLoadBalance {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Server doSelect(List<Server> servers) {
        if (count.get() >= servers.size()) {
            count.set(0);
        }

        return servers.get(count.getAndIncrement());
    }
}
