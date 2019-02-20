package com.panda.demo.dt.jta.business;

import com.panda.demo.dt.jta.common.util.UuidUtil;
import com.panda.demo.dt.jta.member.mapper.AccountMapper;
import com.panda.demo.dt.jta.member.model.Account;
import com.panda.demo.dt.jta.trade.mapper.OrderMapper;
import com.panda.demo.dt.jta.trade.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author huixiangdou
 * @date 2019-02-20
 */
@Service
public class BusinessService implements IBusinessService {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public void process() {
        int money = 20;
        //扣款
        Account account = accountMapper.findByUserId("a");
        account.setMoney(account.getMoney() - money);
        accountMapper.updateByPrimaryKeySelective(account);
        //出单
        Order order = new Order();
        order.setId(UuidUtil.uuid());
        order.setCommodityCode("apple");
        order.setUserId("a");
        order.setNum(1);
        order.setMoney(money);
        orderMapper.insertSelective(order);

        if (true) {
            throw new RuntimeException("抛出异常数据回滚");
        }
    }

    @Override
    @Transactional
    public void initAccount() {
        Account account = new Account();
        account.setId(UuidUtil.uuid());
        account.setMoney(100);
        account.setUserId("a");
        accountMapper.insertSelective(account);
    }
}
