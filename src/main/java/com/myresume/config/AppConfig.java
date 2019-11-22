package com.myresume.config;

import com.myresume.filter.CustomSiteMashFilter;
import com.myresume.filter.ErrorFilter;
import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
//import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
//import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
//import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@PropertySource({"classpath:logic.properties"})
@EnableSpringDataWebSupport
@EnableAutoConfiguration
// (exclude={ElasticsearchAutoConfiguration.class
//        , RestClientAutoConfiguration.class
//})
@EnableJpaRepositories("com.myresume.repository")
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

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


}
