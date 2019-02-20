package com.panda.demo.dt.jta.trade.model;

import java.io.Serializable;

public class Order implements Serializable {
    /**
     *
     */
    private String id;

    /**
     *
     */
    private String userId;

    /**
     *
     */
    private String commodityCode;

    /**
     *
     */
    private Integer num;

    /**
     *
     */
    private Integer money;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}