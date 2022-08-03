package com.lld.app.service;

import com.lld.app.common.ResponseVO;
import com.lld.app.dao.User;
import com.lld.app.model.req.RegisterReq;

public interface UserService {

    public ResponseVO<User> getUserByUserNameAndPassword(String userName, String password);

    public ResponseVO<User> getUserByMobile(String mobile);

    public ResponseVO<User> getUserById(Integer userId);

    public ResponseVO<User> registerUser(RegisterReq req);

}
