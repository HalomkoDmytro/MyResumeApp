package com.myresume.config;

import com.myresume.filter.CustomSiteMashFilter;
import com.myresume.filter.ErrorFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:logic.properties")
public class AppConfig extends SpringBootServletInitializer {

    @Value("${application.production}")
    private boolean isProduction;

    @Bean
    public FilterRegistrationBean<CustomSiteMashFilter> siteMeshFilter() {
        FilterRegistrationBean<CustomSiteMashFilter> filterRegistrationBean = new FilterRegistrationBean<CustomSiteMashFilter>();
        filterRegistrationBean.setFilter(new CustomSiteMashFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<ErrorFilter> errorFilter() {
        FilterRegistrationBean<ErrorFilter> errorFilter = new FilterRegistrationBean<>();
        errorFilter.setFilter(new ErrorFilter(isProduction));
        errorFilter.addUrlPatterns("/*");
        errorFilter.setOrder(1);
        return errorFilter;
    }
}
