package com.lld.app.model.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: lld
 * @createDate: 2021/3/24
 * @version: 1.0
 */
@Data
public class GetRedPacketStatusReq {

    @NotEmpty(message = "账号不能为空")
    private Integer userId;

    @NotNull(message = "红包id不能为空")
    private Long redPacketId;
}
