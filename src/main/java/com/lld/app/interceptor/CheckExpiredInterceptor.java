package com.lld.app.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.lld.app.common.ResponseVO;
import com.lld.app.config.AppConfig;
import com.lld.app.dao.User;
import com.lld.app.service.UserService;
import com.lld.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Optional;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-04 11:02
 **/
public class CheckExpiredInterceptor implements HandlerInterceptor {

    @Autowired
    AppConfig appConfig;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");// 从 http 请求头中取出 token

        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        // 执行认证
        if (token == null) {
            resp(response);
        }

        // 验证 token
        Optional<String> userId = JwtUtils.getClaims(token, "userId");
        if(userId.get() == null){
            resp(response);
        }

        ResponseVO<User> userById = userService.getUserById(Integer.valueOf(userId.get()));
        if(!userById.isOk()){
            resp(response);
        }

        RequestHolder.set(userId.get());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void resp(HttpServletResponse response) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {

            ResponseVO vo = ResponseVO.errorResponse(401, "用户未登录或登录失效");
            String resp = JSONObject.toJSONString(vo);
            writer = response.getWriter();
            writer.print(resp);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }

    }
}
