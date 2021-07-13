package com.example.PS13223_TranNgocPhu_ASM;

import com.example.PS13223_TranNgocPhu_ASM.Service.AuthInterceptor;
import com.example.PS13223_TranNgocPhu_ASM.Service.GlobalInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    GlobalInterceptor global;
    @Autowired
    AuthInterceptor auth;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(global).addPathPatterns("/**").excludePathPatterns("/assets/**");

        registry.addInterceptor(auth).addPathPatterns("/cart/view");

    }

}
