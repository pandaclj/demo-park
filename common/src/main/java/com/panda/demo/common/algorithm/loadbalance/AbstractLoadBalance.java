package com.panda.demo.common.algorithm.loadbalance;

import java.util.List;

/**
 * 负载均衡
 *
 * @author huixiangdou
 * @date 2019-03-19
 */
public abstract class AbstractLoadBalance {
    /**
     * 利用算法选取一台Server
     *
     * @param servers
     * @return
     */
    public Server select(List<Server> servers){
        if (servers.size() == 0) {
            return null;
        }
        if (servers.size() == 1) {
            return servers.get(0);
        }
        return doSelect(servers);
    }

    public abstract Server doSelect(List<Server> servers);
}
