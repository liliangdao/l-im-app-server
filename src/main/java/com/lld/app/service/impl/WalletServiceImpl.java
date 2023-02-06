package com.lld.app.service.impl;

import com.lld.app.dao.UserWallet;
import com.lld.app.dao.UserWalletLog;
import com.lld.app.dao.mapper.UserWalletLogMapper;
import com.lld.app.dao.mapper.UserWalletMapper;
import com.lld.app.enums.ErrorCode;
import com.lld.app.exception.ApplicationException;
import com.lld.app.service.UserService;
import com.lld.app.service.WalletService;
import com.lld.app.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 16:25
 **/
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    UserWalletMapper userWalletMapper;

    @Autowired
    UserWalletLogMapper userWalletLogMapper;

    @Autowired
    UserService userService;

    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;

    @Override
    @Transactional
    public int dealWallet(Integer userId, BigDecimal amount, Integer type) {

        UserWallet accountWallet = userWalletMapper.selectById(userId);

        int i = userWalletMapper.updateAmount(userId, amount);
        if(i != 1){
            throw new ApplicationException(ErrorCode.ACCOUNTWALLET_AMOUNT_INSU);
        }
//
        UserWalletLog log = new UserWalletLog();
        log.setId(snowflakeIdWorker.nextId());
        log.setAmount(amount);
        log.setBeForeAmount(accountWallet.getAmount().add(amount));
        log.setType(type);
        log.setCreateTime(System.currentTimeMillis());
        log.setUserId(userId);
        userWalletLogMapper.insert(log);
        return i;
    }
}
