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
import com.lld.app.interceptor.RequestHolder;
import com.lld.app.model.req.OpenRedPacketReq;
import com.lld.app.model.req.SendRedPacketReq;
import com.lld.app.service.RedPacketService;
import com.lld.app.service.UserService;
import com.lld.app.service.WalletService;
import com.lld.app.utils.SnowflakeIdWorker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    WalletService walletService;

    @Override
    @Transactional
    public Long sendRedPacket(SendRedPacketReq req) {

        Integer userId = RequestHolder.get();

        RedPacket redPacket = new RedPacket();
        BeanUtils.copyProperties(req, redPacket);
        long redpacketId = snowflakeIdWorker.nextId();
        redPacket.setRedPacketId(redpacketId);
        redPacket.setCreateTime(BigInteger.valueOf(System.currentTimeMillis()));
        redPacket.setStatus(0);
        redPacket.setType(req.getType());
        redPacket.setNumber(req.getNumber());
        redPacket.setAmount(req.getAmount());
        redPacketMapper.insert(redPacket);

        walletService.dealWallet(userId,req.getAmount(),req.getType());

        //扣款成功后生成redis抢包记录 投入过期消息到mq
        List<RedPacketDetail> redPacketDetail = createRedPacketDetail(redPacket);
//        redPacketRedis.setRedPacketDetailList(redPacket.getRedPacketId(), redPacketDetail);
//        redPacketMsgProducer.sendRedPackerExpire(redPacket.getRedPacketId());

        return redpacketId;
    }

    private List<RedPacketDetail> createRedPacketDetail(RedPacket redPacket) {
        List<RedPacketDetail> list = new ArrayList<>(redPacket.getNumber());

        BigDecimal money = redPacket.getAmount();
        for (int i = 0; i < redPacket.getNumber(); i++) {
            BigDecimal nowMoney = new BigDecimal(0);
            nowMoney = sjmoney((redPacket.getNumber() + 1) - i, money);
            money = money.subtract(nowMoney);
            RedPacketDetail detail = new RedPacketDetail();
            detail.setStatus(0);
            detail.setAmount(nowMoney);
            detail.setRedPacketId(redPacket.getRedPacketId());
            list.add(detail);
        }
        return list;
    }

    /**
     * 抢红包算法
     *
     * @param count       数量
     * @param remainMoney 金额
     * @return 9 8
     */
    private static BigDecimal sjmoney(int count, BigDecimal remainMoney) {
        BigDecimal uswermoney = new BigDecimal(0);
        if (count == 1) {
            count--;
            uswermoney = remainMoney.multiply(new BigDecimal(100)).divide(new BigDecimal(100));
            return uswermoney;
        }
        Random r = new Random();

        BigDecimal min = new BigDecimal("0.01");
        BigDecimal max = remainMoney.divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_UP);
        max = max.multiply(new BigDecimal(2));
        BigDecimal v = max.multiply(new BigDecimal(r.nextDouble()));
        BigDecimal money = v;

        if (money.compareTo(min) <= 0)
            money = new BigDecimal(0.01);
        count--;

        return money;
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
    public Integer getRedPacketStatus(Long redPacketId, Integer account) {
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
