package com.lld.app.model.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 09:28
 **/
@Data
public class SearchUserReq {

    @NotBlank(message = "关键字不能为空")
    private String keyWord;

    @NotNull(message = "搜索方式不能为空")
    private Integer searchType;

}
