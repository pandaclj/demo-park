package com.panda.demo.common.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.panda.demo.model.Student;

import java.util.HashMap;

/**
 * @author huixiangdou
 * @date 2020/5/8
 */
public class FastJson {
    public static void main(String[] args) {
        String str = "{\"id\":\"1\",\"name\":\"小明\"}";

        Student student = JSON.parseObject(str, Student.class);

        JSONObject jsonObject = JSONObject.parseObject(str);

        HashMap hashmap = JSON.parseObject(str,HashMap.class);

        System.out.println("over");

    }
}
