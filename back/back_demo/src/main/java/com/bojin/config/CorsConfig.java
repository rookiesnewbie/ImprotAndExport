package com.bojin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 解决跨域问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {



    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //拦截所有请求
                .allowedOrigins("*")  //可跨域的域名
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")// 允许跨域的方法
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");// 允许跨域的请求头
    }

    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
    //    registry.addInterceptor(new LoginInterceptor(userService))
    //            .addPathPatterns("/**")   //拦截所有请求，通过判断token是否合法来决定是否需要登录
    //            .excludePathPatterns("/admin/user/login","/player/login","/swagger-resources/**","/webjars/**","/swagger-ui.html/**","/doc.html","/v2/api-docs");//这些路径放行
    //
    //}


    /**
     * 配置静态只有访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //配置访问静态资源的路径，注意：addResourceLocations参数的字符串必须以`file:`开头，否则访问不到资源
        registry.addResourceHandler("/image/qrCode/**").addResourceLocations("file:D:/file/");  //本地环境
        //registry.addResourceHandler("/image/qrCode/**").addResourceLocations("file:/home/file/");  //线上环境
    }


}
