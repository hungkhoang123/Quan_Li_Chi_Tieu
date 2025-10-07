package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/chi-tieu/**", "/danh-muc/**", "/thong-ke/**") // các trang cần bảo vệ
                .excludePathPatterns("/dang-nhap", "/dang-ky", "/css/**", "/js/**", "/images/**"); // loại trừ trang login/ signup
    }
}

