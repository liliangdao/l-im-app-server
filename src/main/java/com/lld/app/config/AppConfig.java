package com.lld.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-05-16 11:10
 **/
@Data
@Component
@ConfigurationProperties(prefix = "appconfig")
public class AppConfig {

    private String imUrl;

    private String imVersion;

    private String appId;

    private String jwtKey;

    private Integer jwtExpireTime;

    private String redPacketAmountUrl;

}
