package com.jh.demo.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

//    // 注册拦截器
//    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 新增登录校验拦截器 校验规则为 StpUtil.checkLogin() 登录校验。
        SaInterceptor saInterceptor = new SaInterceptor(handle -> StpUtil.checkLogin());
        // 注册 Sa-Token 拦截器
        registry.addInterceptor(saInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/Jh/doLogin","/Jh/doLogin");  // 排除了/Jh/doLogin接口用来开放登录
    }
}
