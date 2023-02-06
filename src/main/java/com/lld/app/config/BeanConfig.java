package com.lld.app.config;

import com.lld.app.utils.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-16 16:44
 **/
@Configuration
public class BeanConfig {

    @Bean
    public SnowflakeIdWorker getSnowflakeIdWorker(){
        return new SnowflakeIdWorker(0,0);
    }

}
