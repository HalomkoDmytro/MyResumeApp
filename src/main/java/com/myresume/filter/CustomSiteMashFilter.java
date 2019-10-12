package com.myresume.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomSiteMashFilter extends ConfigurableSiteMeshFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSiteMashFilter.class);

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*","/WEB-INF/template/page_template.jsp");
    }
}
