package com.panda.demo.dt.jta.test;

import com.panda.demo.dt.jta.business.IBusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author huixiangdou
 * @date 2019-02-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DemoTest {
    @Resource
    private IBusinessService businessService;

    @Test
    public void process() {
        businessService.process();
    }

    @Test
    public void init() {
        businessService.initAccount();
    }

}
