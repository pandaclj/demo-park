package com.panda.demo.common.algorithm.loadbalance;

/**
 * 服务器
 *
 * @author huixiangdou
 * @date 2019-03-19
 */
public class Server {
    /**
     * IP
     */
    private String ip;
    /**
     * 权重
     */
    private int weight;

    private int currentWeight;

    public Server(String ip, int weight) {
        this.ip = ip;
        this.weight = weight;
    }

    public Server(String ip, int weight, int currentWeight) {
        this.ip = ip;
        this.weight = weight;
        this.currentWeight = currentWeight;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }
}
