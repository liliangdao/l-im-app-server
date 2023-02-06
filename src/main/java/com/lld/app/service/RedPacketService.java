package com.lld.app.service;


import com.lld.app.common.ResponseVO;
import com.lld.app.dao.RedPacket;
import com.lld.app.model.req.OpenRedPacketReq;
import com.lld.app.model.req.SendRedPacketReq;

/**
 * @author lld
 * @version 1.0
 * @date 2021/3/15 11:05
 */
public interface RedPacketService {

    public Long sendRedPacket(SendRedPacketReq req);

    public ResponseVO openRedPacket(OpenRedPacketReq req) ;

    public RedPacket getRedPacket(Long redPacketId);

    public Integer getRedPacketStatus(Long redPacketId, Integer account);

    public void redPacketExpire(Long redPacketId) throws InterruptedException;

}
