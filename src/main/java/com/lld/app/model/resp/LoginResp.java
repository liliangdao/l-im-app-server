package com.lld.app.model.resp;

import lombok.Data;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 09:35
 **/
@Data
public class LoginResp {

    //im的token
    private String imUserSign;

    //自己的token
    private String userSign;

    private String userId;

    private Integer appId;

}
