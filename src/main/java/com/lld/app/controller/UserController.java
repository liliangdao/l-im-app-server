package com.lld.app.controller;

import com.lld.app.common.ResponseVO;
import com.lld.app.model.req.SearchUserReq;
import com.lld.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lld
 * @createDate: 2022-08-07
 * @version: 1.0
 */
@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @description: 搜索用户
     * @param
     * @return com.lld.app.common.ResponseVO
     * @author lld
     * @since 2022-08-07
     */
    @RequestMapping("/search")
    public ResponseVO search(@RequestBody @Validated SearchUserReq req) {
        return userService.searchUser(req);
    }

}
