package com.lld.app.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lld.app.common.ResponseVO;
import com.lld.app.dao.User;
import com.lld.app.dao.mapper.UserMapper;
import com.lld.app.enums.ErrorCode;
import com.lld.app.model.proto.ImportUserProto;
import com.lld.app.model.req.RegisterReq;
import com.lld.app.model.resp.ImportUserResp;
import com.lld.app.service.ImService;
import com.lld.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 09:51
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ImService imService;

    /**
     * @param [userName, password]
     * @return com.lld.app.common.ResponseVO
     * @description 根据用户名和密码获取用户
     * @author chackylee
     * @date 2022/8/3 9:52
     */
    @Override
    public ResponseVO getUserByUserNameAndPassword(String userName, String password) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName);
        wrapper.eq("password", password);
        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            return ResponseVO.errorResponse(ErrorCode.USER_NOT_EXIST);
        }

        return ResponseVO.successResponse(user);
    }

    /**
     * @param [mobile]
     * @return com.lld.app.common.ResponseVO
     * @description 根据手机号获取用户
     * @author chackylee
     * @date 2022/8/3 9:52
     */
    @Override
    public ResponseVO getUserByMobile(String mobile) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            return ResponseVO.errorResponse(ErrorCode.USER_NOT_EXIST);
        }
        return ResponseVO.successResponse(user);
    }

    /**
     * @param [userId]
     * @return com.lld.app.common.ResponseVO
     * @description 根据用户id获取用户
     * @author chackylee
     * @date 2022/8/3 9:53
     */
    @Override
    public ResponseVO getUserById(Integer userId) {

        User user = userMapper.selectById(userId);
        if (user == null) {
            return ResponseVO.errorResponse(ErrorCode.USER_NOT_EXIST);
        }
        return ResponseVO.successResponse(user);

    }

    @Override
    public ResponseVO<User> registerUser(RegisterReq req) {

        User user = new User();
        user.setCreateTime(System.currentTimeMillis());
        user.setMobile(req.getMobile());
        user.setPassword(req.getPassword());
        user.setUserName(RandomUtil.randomString(16));
        userMapper.insert(user);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        ResponseVO responseVO = imService.importUser(users);
        if(responseVO.isOk()){
            Object data = responseVO.getData();
            ObjectMapper objectMapper = new ObjectMapper();
            ImportUserResp importUserResp = objectMapper.convertValue(data, ImportUserResp.class);

            Set<String> successId = importUserResp.getSuccessId();
            if(successId.contains(user.getUserId().toString())){
                return ResponseVO.successResponse(user);
            }
        }else{

        }

        return null;
    }
}
