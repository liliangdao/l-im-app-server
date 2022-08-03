package com.lld.app.service;

import com.alibaba.fastjson.JSONObject;
import com.lld.app.common.ResponseVO;
import com.lld.app.config.AppConfig;
import com.lld.app.dao.User;
import com.lld.app.model.proto.ImportUserProto;
import com.lld.app.model.resp.ImportUserResp;
import com.lld.app.utils.HttpRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 10:36
 **/
@Service
public class ImService {

    @Autowired
    HttpRequestUtils httpRequestUtils;

    @Autowired
    AppConfig appConfig;

    public volatile static Map<String, Object> parameter;

    private String getUrl(String uri) {
        return appConfig.getImUrl() + "/" + appConfig.getImVersion() + uri;
    }

    private Map<String, Object> getParamter() {
        if (parameter == null) {
            synchronized (parameter) {
                if(parameter == null){
                    parameter = new ConcurrentHashMap<>();
                    parameter.put("appId",appConfig.getAppId());
                }
            }
        }
        return parameter;
    }

    public ResponseVO<ImportUserResp> importUser(List<User> users) {

        List<ImportUserProto.UserData> userData = new ArrayList<>();
        users.forEach(e -> {
            ImportUserProto.UserData u = new ImportUserProto.UserData();
            u.setUserId(e.getUserId().toString());
//            u.setPhoto(e.get);
            u.setUserType(1);
            userData.add(u);
        });

        String uri = "/user/import";

        try {
            ResponseVO responseVO = httpRequestUtils.doPost(getUrl(uri), ResponseVO.class, getParamter(), null, JSONObject.toJSONString(userData), "");
            return responseVO;
        }catch (Exception e){
            e.printStackTrace();;
        }

        return null;
    }


}
