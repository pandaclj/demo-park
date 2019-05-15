package com.panda.demo.common.algorithm.loadbalance;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 平滑加权轮询
 *
 * @author huixiangdou
 * @date 2019-03-19
 */
public class SmoothWeightRoundRobinLoadBalance extends AbstractLoadBalance {

    @Override
    public Server doSelect(List<Server> servers) {
        //计算总权值
        int total = total(servers);
        //初始化currentWeight
        init(servers);

        Server server = max(servers);
        server.setCurrentWeight(server.getCurrentWeight() - total);
        print(servers);
        return server;
    }

    private int total(List<Server> servers) {
        int total = 0;
        for (Server server : servers) {
            total += server.getWeight();
        }
        return total;
    }

    //初始化currentWeight
    private void init(List<Server> servers) {
        servers.forEach(server -> server.setCurrentWeight(server.getWeight() + server.getCurrentWeight()));
        print(servers);
    }

    private Server max(List<Server> servers) {
        int maxIndex = 0;
        int max = servers.get(maxIndex).getCurrentWeight();
        for (int i = 1; i < servers.size(); i++) {
            int tmp = servers.get(i).getCurrentWeight();
            if(tmp > max){
                max = tmp;
                maxIndex = i;
            }
        }
        return servers.get(maxIndex);
    }

    private void print(List<Server> servers){
        List<Integer> list = servers.stream().map(Server::getCurrentWeight).collect(Collectors.toList());
        System.out.println("{" + Joiner.on(",").join(list) + "}");
    }

    public static void main(String[] args) {
        SmoothWeightRoundRobinLoadBalance loadBalance = new SmoothWeightRoundRobinLoadBalance();
        List<Server> servers = Lists.newArrayList(new Server("a", 4), new Server("b", 2), new Server("c", 1));
        for (int i = 0; i < 7; i++) {
            System.out.println(loadBalance.select(servers).getIp());
        }
    }
}
