package com.lld.app.model.req;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 09:28
 **/
@Data
public class RegisterReq {

    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "请选择注册方式")
    //登录方式 1手机号注册
    private Integer registerType;

    private String proto;

}
