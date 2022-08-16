package com.lld.app.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 15:01
 **/
@Data
@TableName("app_report_tag")
public class ReportTag {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String tagName;

    private Integer parentId;

    private Integer sort;

    //创建时间
    private Long createTime;
    //更新时间
    private Long updateTime;
}
