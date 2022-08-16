package com.lld.app.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 09:38
 **/
@TableName("app_user_wallet")
@Data
public class UserWallet {

    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private String userId;

    private BigDecimal amount;

    private Long createTime;

    private Long lastUpdateTime;

}
