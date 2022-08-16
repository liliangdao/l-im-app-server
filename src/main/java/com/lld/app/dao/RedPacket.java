package com.lld.app.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@TableName("app_red_packet")
@Data
public class RedPacket {
    //红包id
    @TableId(value = "red_packet_id", type = IdType.AUTO)
    private Long redPacketId;
    //红包标题
    private String title;
    //红包描述
    private String description;
    //红包发送者id
    private Integer sender;
    //红包当前状态 0初始化(可抢) 1抢完 2过期
    private int status;
    //红包类型
    private int type;
    //红包个数
    private int number;
    //红包已经开启的个数 待定
    private int isOpenNumber;
    //红包金额
    private BigDecimal amount;
    //创建时间/发送时间
    private BigInteger createTime;
}
