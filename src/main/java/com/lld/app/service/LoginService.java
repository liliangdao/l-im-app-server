package com.lld.app.service;

import com.lld.app.common.ResponseVO;
import com.lld.app.model.req.LoginReq;
import com.lld.app.model.req.RegisterReq;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 09:27
 **/
public interface LoginService {

    public ResponseVO login(LoginReq req);

    public ResponseVO register(RegisterReq req);
}
