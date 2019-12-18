package com.myresume.config;

import com.myresume.filter.CustomSiteMashFilter;
import com.myresume.filter.ErrorFilter;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:logic.properties"})
@EnableSpringDataWebSupport
@EnableAutoConfiguration
@EnableJpaRepositories("com.myresume.repository")
public class AppConfig extends SpringBootServletInitializer {

    @Value("${application.production}")
    private boolean isProduction;

    @Autowired
    private Environment environment;

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
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewNames("jsp/*");
        viewResolver.setOrder(0);
        return viewResolver;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
        dataSource.setInitialSize(Integer.parseInt(environment.getRequiredProperty("spring.datasource.dbcp2.initial-size")));
        dataSource.setMaxTotal(Integer.parseInt(environment.getRequiredProperty("spring.datasource.dbcp2.max-total")));
        return dataSource;
    }

}
