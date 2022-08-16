package com.lld.app.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;


@TableName("red_packet_detail")
@Setter
@Getter
public class RedPacketDetail {
    //主键id
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    //红包id
    private Long redPacketId;
    //状态
    private int status;
    //抢到的金额
    private BigDecimal amount;
    //创建时间(抢包时间)
    private BigInteger createTime;

    private Integer account;
}
