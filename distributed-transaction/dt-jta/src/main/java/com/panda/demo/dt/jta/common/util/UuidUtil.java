package com.panda.demo.dt.jta.common.util;

import java.util.UUID;

/**
 * @author huixiangdou
 * @date 2019-02-20
 */
public class UuidUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.printf(UuidUtil.uuid());
    }
}
