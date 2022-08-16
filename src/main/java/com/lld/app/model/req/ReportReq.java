package com.lld.app.model.req;

import lombok.Data;

import java.util.List;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 15:08
 **/
@Data
public class ReportReq {

    //内容
    private String content;

    //文件
    private List<String> file;

    //举报类型 人or群 1人 2群
    private Integer reportType;

    //被举报人ID
    private Integer reportedId;

    //举报标签id
    private Integer reportTagId;

}
