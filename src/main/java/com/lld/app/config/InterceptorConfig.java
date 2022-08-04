package com.lld.app.config;

import com.lld.app.interceptor.CheckExpiredInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-04 11:15
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkExpiredInterceptor())
                .addPathPatterns("/**")
        .excludePathPatterns("/**/login","/**/register");
    }

    @Bean
    public CheckExpiredInterceptor checkExpiredInterceptor(){
        return new CheckExpiredInterceptor();
    }

}
