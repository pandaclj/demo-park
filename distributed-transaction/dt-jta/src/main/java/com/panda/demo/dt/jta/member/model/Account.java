package com.panda.demo.dt.jta.member.model;

import java.io.Serializable;

public class Account implements Serializable {
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

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}