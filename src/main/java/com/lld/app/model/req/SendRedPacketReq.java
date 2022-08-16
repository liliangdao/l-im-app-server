package com.lld.app.model.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * @author lld
 * @version 1.0
 * @date 2021/3/15 9:58
 */
@Getter
@Setter
public class SendRedPacketReq {
    @NotNull(message = "发送人不能为空")
    private Integer userId;
    @NotNull(message = "金额不能为空")
    private BigDecimal amount;
    @NotNull(message = "数量不能为空")
    private Integer number;
    @NotNull(message = "类型不能为空")
    private Integer type;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "描述不能为空")
    private String description;

}
