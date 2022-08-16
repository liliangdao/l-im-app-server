package com.lld.app.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 15:02
 **/
@Data
@TableName("app_report")
public class Report {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    //举报类型 群or用户 1用户 2群
    private Integer reportType;

    private Integer reportedId;

    private String content;

    private Integer reportTagId;

    //处理状态 0未处理 1已拒绝 2确认有问题
    private Integer processStatus;

    //创建时间
    private Long createTime;
    //更新时间
    private Long updateTime;
}
