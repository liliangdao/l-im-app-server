package com.lld.app.controller;

import com.lld.app.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 13:43
 **/
@RestController
@RequestMapping("v1/redPacket")
public class RedPacketController {

    @Autowired
    RedPacketService redPacketService;

}
