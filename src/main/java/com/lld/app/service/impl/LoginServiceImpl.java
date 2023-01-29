package com.lld.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lld.app.common.ResponseVO;
import com.lld.app.config.AppConfig;
import com.lld.app.dao.User;
import com.lld.app.dao.mapper.UserMapper;
import com.lld.app.enums.ErrorCode;
import com.lld.app.enums.LoginTypeEnum;
import com.lld.app.enums.RegisterTypeEnum;
import com.lld.app.model.req.LoginReq;
import com.lld.app.model.req.RegisterReq;
import com.lld.app.model.resp.LoginResp;
import com.lld.app.service.LoginService;
import com.lld.app.service.UserService;
import com.lld.app.utils.JwtUtils;
import com.lld.app.utils.TLSSigAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 09:34
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AppConfig appConfig;

    /**
     * @param [req]
     * @return com.lld.app.common.ResponseVO
     * @description 登录服务，需返回im userSign，和app的userSign
     * @author chackylee
     * @date 2022/8/3 9:34
     */
    @Override
    public ResponseVO login(LoginReq req) {

        LoginResp loginResp = new LoginResp();

        if (LoginTypeEnum.USERNAME_PASSWORD.getCode() == req.getLoginType()) {
            ResponseVO<User> userResp = userService.getUserByUserNameAndPassword(req.getUserName(), req.getPassword());
            if (userResp.isOk()) {
                User user = userResp.getData();
                String key = JwtUtils.makeToken(user.getUserId());
                TLSSigAPI tlsSigAPI = new TLSSigAPI(10000, "123456");
                loginResp.setImUserSign(tlsSigAPI.genUserSig(user.getUserId(),1800000L));
                loginResp.setUserSign(key);
                loginResp.setAppId(appConfig.getAppId());
                loginResp.setUserId(user.getUserId());
            } else if (userResp.getCode() == ErrorCode.USER_NOT_EXIST.getCode()) {
                return ResponseVO.errorResponse(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
            } else {
                return userResp;
            }

        } else if (LoginTypeEnum.SMS_CODE.getCode() == req.getLoginType()) {

        }

        return ResponseVO.successResponse(loginResp);
    }

    /**
     * @description 注册我们的服务并向im导入用户
     * @author chackylee
     * @date 2022/8/3 10:25
     * @param [req]
     * @return com.lld.app.common.ResponseVO
    */
    @Override
    @Transactional
    public ResponseVO register(RegisterReq req) {

        if(RegisterTypeEnum.MOBILE.getCode() == req.getRegisterType()){
            ResponseVO<User> userByMobile = userService.getUserByMobile(req.getMobile());
            if(userByMobile.isOk()){
                return ResponseVO.errorResponse(ErrorCode.MOBILE_IS_REGISTER);
            }
            userService.registerUser(req);
        }
        return ResponseVO.successResponse();
    }


}
