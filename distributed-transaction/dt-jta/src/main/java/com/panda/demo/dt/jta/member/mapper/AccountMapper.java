package com.panda.demo.dt.jta.member.mapper;

import com.panda.demo.dt.jta.member.model.Account;import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    Account findByUserId(@Param("userId") String userId);
}