package com.lld.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.lld.app.common.ResponseVO;
import com.lld.app.model.req.LoginReq;
import com.lld.app.model.req.RegisterReq;
import com.lld.app.service.LoginService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
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

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/login")
    public ResponseVO login(@RequestBody @Validated LoginReq req) {

        return loginService.login(req);
    }

    @RequestMapping("/test")
    public ResponseVO test(@RequestBody @Validated Object req) {
        rabbitTemplate.convertAndSend("pipeline2MessageService","",JSONObject.toJSONString(req));
        return ResponseVO.successResponse();
    }

    @RequestMapping("/testNumber")
    public ResponseVO test(@RequestBody @Validated Object req,Integer number) {
        for (int i = 0; i < number; i++) {
            JSONObject o = (JSONObject)JSONObject.toJSON(req);
            o.put("messageId",o.getString("messageId") + i);
            rabbitTemplate.convertAndSend("pipeline2MessageService","",o.toJSONString());
        }
        return ResponseVO.successResponse();
    }

    @RequestMapping("/register")
    public ResponseVO register(@RequestBody @Validated RegisterReq req) {
        return loginService.register(req);
    }

}
