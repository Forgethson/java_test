package com.wjd.admin.service.impl;

import com.wjd.admin.bean.Account;
import com.wjd.admin.mapper.AccountMapper;
import com.wjd.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    public Account getAcctByid(Long id){
        return accountMapper.getAcct(id);
    }
}
