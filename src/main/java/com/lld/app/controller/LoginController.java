package com.lld.app.controller;

import com.lld.app.common.ResponseVO;
import com.lld.app.model.req.LoginReq;
import com.lld.app.model.req.RegisterReq;
import com.lld.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 09:25
 **/
@RestController
@RequestMapping("v1")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public ResponseVO login(@RequestBody @Validated LoginReq req) {
        return loginService.login(req);
    }

    @RequestMapping("/register")
    public ResponseVO register(@RequestBody @Validated RegisterReq req) {
        return loginService.register(req);
    }

}
