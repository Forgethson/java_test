package com.wjd.admin.mapper;

import com.wjd.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {


    public Account getAcct(Long id);
}
