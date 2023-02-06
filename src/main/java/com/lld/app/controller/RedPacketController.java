package com.lld.app.controller;

import com.lld.app.common.ResponseVO;
import com.lld.app.model.req.GetRedPacketStatusReq;
import com.lld.app.model.req.OpenRedPacketReq;
import com.lld.app.model.req.SendRedPacketReq;
import com.lld.app.service.RedPacketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private static Logger logger = LoggerFactory.getLogger(RedPacketController.class);

    @Autowired
    RedPacketService redPacketService;

    @PostMapping("/sendRedPacket")
    public ResponseVO sendRedPacket(@RequestBody @Validated SendRedPacketReq req){
        logger.info("/sendRedPacket : ");
        Long aLong = redPacketService.sendRedPacket(req);

        return ResponseVO.successResponse(aLong);
    }

    @PostMapping("/openRedPacket")
    public ResponseVO openRedPacket(@RequestBody @Validated OpenRedPacketReq req) {
        logger.info("/openRedPacket : ");
        ResponseVO ResponseVO = redPacketService.openRedPacket(req);
        return ResponseVO;
    }

    @PostMapping("/getRedPacketStatus")
    public ResponseVO getRedPacketStatus(@RequestBody @Validated GetRedPacketStatusReq req)  {
        logger.info("/getRedPacketStatus : ");
        Integer redPacketStatus = redPacketService.getRedPacketStatus(req.getRedPacketId(), req.getUserId());
        return ResponseVO.successResponse(redPacketStatus);
    }

}
