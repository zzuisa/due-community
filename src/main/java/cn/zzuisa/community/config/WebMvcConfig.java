package cn.zzuisa.community.config;

import cn.zzuisa.community.controller.interceptor.LoginRequiredInterceptor;
import cn.zzuisa.community.controller.interceptor.LoginTicketInterceptor;
import cn.zzuisa.community.controller.interceptor.MessageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ao
 * @date 2019-10-27 13:58
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    @Autowired
    private LoginRequiredInterceptor loginRequiredInterceptor;

    @Autowired
    MessageInterceptor messageInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginTicketInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
        registry.addInterceptor(loginRequiredInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
        registry.addInterceptor(messageInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");

    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // All static assets related to pages are placed under the project's static directory.
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/upload");
        // Uploaded images are stored under the OTA directory on drive D. Example access URL:
        // http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        // Here, "OTA" is the URL prefix, and "file:D:/OTA/" is the actual storage path.
//        registry.addResourceHandler("/**").addResourceLocations("/Users/frank/Desktop/upload");
    }


}
