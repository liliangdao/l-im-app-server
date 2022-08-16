package com.lld.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lld.app.common.ResponseVO;
import com.lld.app.dao.RedPacket;
import com.lld.app.dao.RedPacketDetail;
import com.lld.app.dao.mapper.RedPacketDetailMapper;
import com.lld.app.dao.mapper.RedPacketMapper;
import com.lld.app.enums.ErrorCode;
import com.lld.app.enums.RedPacketStatusEnum;
import com.lld.app.exception.ApplicationException;
import com.lld.app.model.req.OpenRedPacketReq;
import com.lld.app.model.req.SendRedPacketReq;
import com.lld.app.service.RedPacketService;
import com.lld.app.service.UserService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 15:40
 **/
@Service
public class RedPacketServiceImpl implements RedPacketService {

    @Autowired
    RedPacketMapper redPacketMapper;

    @Autowired
    RedPacketDetailMapper redPacketDetailMapper;

    @Autowired
    UserService userService;

    @Autowired
    RedissonClient redissonClient;

    @Override
    public Long sendRedPacket(SendRedPacketReq req) {
        return null;
    }

    @Override
    public ResponseVO openRedPacket(OpenRedPacketReq req) {
        return null;
    }

    @Override
    public RedPacket getRedPacket(Long redPacketId) {
        return redPacketMapper.selectById(redPacketId);
    }

    @Override
    public Integer getRedPacketStatus(Long redPacketId, String account) {
        RedPacket redPacket = getRedPacket(redPacketId);
        if (redPacket == null) {
            throw new ApplicationException(ErrorCode.REDPACKET_IS_NOT_EXIST);
        }

        QueryWrapper<RedPacketDetail> wrapper = new QueryWrapper();
        wrapper.eq("red_packet_id", redPacketId).eq("account", account);
        RedPacketDetail redPacketDetail = redPacketDetailMapper.selectOne(wrapper);
        if (redPacketDetail != null) {
            return RedPacketStatusEnum.ISOPENED.getStatus();
        }

        return redPacket.getStatus();
    }

    @Override
    public void redPacketExpire(Long redPacketId) throws InterruptedException {
    }
}
