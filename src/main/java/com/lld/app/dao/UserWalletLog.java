package com.lld.app.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 16:12
 **/
@Data
@TableName("app_user_wallet_log")
public class UserWalletLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Integer userId;
    private BigDecimal amount;//操作金额
    private BigDecimal beForeAmount;//之后金额
    private Integer type; // 消费类型
    private Long createTime;
    private String expandId;//拓展号，用来存放业务id，例如红包id，订单id

}
