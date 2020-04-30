package com.myresume.component.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class ApplicationListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    @Value("${application.production}")
    private String isProduction;

    @Value("${application.css.common.version}")
    private String cssCommonVersion;

    @Value("${application.js.common.version}")
    private String jsCommonVersion;

    @Value("${application.host}")
    private String applicationHost;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("production", isProduction);
        sce.getServletContext().setAttribute("host", applicationHost);
        sce.getServletContext().setAttribute("jsCommonVersion", jsCommonVersion);
        sce.getServletContext().setAttribute("cssCommonVersion", cssCommonVersion);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
