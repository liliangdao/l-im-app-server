package com.lld.app.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lld
 * @version 1.0
 * @date 2021/3/19 15:04
 */
@Data
public class OpenRedPacketReq {

    @NotNull(message = "红包id不能为空")
    private Long redPacketId;

    @NotNull(message = "用户id不能为空")
    private Integer userId;

}
