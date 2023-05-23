package com.example.sbprojectex.config;

import javax.servlet.ServletContextListener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.sbprojectex.Listener.CustomContextListener;

@Configuration
public class webConfig implements WebMvcConfigurer{
    
    //  Listener
    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> customListenerBean(){
        ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<ServletContextListener>();
        bean.setListener(new CustomContextListener());
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/");
    }

    

    //  add request listener
    // @Bean
    // public ServletListenerRegistrationBean<RequestListener> requestListenerBean(){
    //     ServletListenerRegistrationBean<RequestListener> bean = new ServletListenerRegistrationBean<RequestListener>();
    //     bean.setListener(new RequestListener());
    //     return bean;
    // }

    //  Filter
    // @Bean
    // public FilterRegistrationBean<Filter> customFilter2(){
    //     FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<Filter>();
    //     filterBean.setFilter(new CustomFilter());
    //     filterBean.addUrlPatterns("/*");
    //     filterBean.setOrder(0);
    //     return filterBean;
    // }
    

    // @Bean 
    // public CommonsRequestLoggingFilter commonsRequestLoggingFitler(){
    //     CommonsRequestLoggingFilter commonsRequestLoggingFilter = new CommonsRequestLoggingFilter();
    //     commonsRequestLoggingFilter.setIncludeQueryString(true);
    //     commonsRequestLoggingFilter.setIncludePayload(true);
    //     commonsRequestLoggingFilter.setMaxPayloadLength(10000);
    //     commonsRequestLoggingFilter.setIncludeHeaders(false);
    //     commonsRequestLoggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
    
    //     return commonsRequestLoggingFilter;
    // }
    



    
}
