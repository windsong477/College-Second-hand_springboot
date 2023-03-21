package com.example.collegesecondhand_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    // 拦截所有请求
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/login","/admin/registerAdmin",
                        "/file/download/{fileUUID}","/goods/getGoodsPage",
                        "/student/login","/student/registerStudent","/goods/getOneGoods",
                        "/collection/**","/leave/**");
    }

    @Bean
    public  JwtInterceptor jwtInterceptor(){

        return new JwtInterceptor();
    }

}