package com.lld.app.service;

import java.math.BigDecimal;

public interface WalletService {

    public int dealWallet(Integer userId, BigDecimal amount,Integer type);

}
