package com.myresume.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorFilter.class);

    private boolean isProduction;

    public ErrorFilter(boolean isProduction) {
        this.isProduction = isProduction;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            filterChain.doFilter(request, response);
        } catch (Throwable e) {
            handleException(request, response, e);
        }
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Throwable e) throws ServletException, IOException {
        if (isProduction) {
            final boolean ifFragmentRequest = request.getRequestURL().toString().startsWith("/fragment");
            if ("/resumeError".equals(request.getRequestURL()) || ifFragmentRequest) {
                sendErrorStatus(response);
            } else {
                response.sendRedirect("/resumeError?url=" + request.getRequestURL());
            }
        } else {
            throw new ServletException(e);
        }
    }

    private void sendErrorStatus(HttpServletResponse response) throws IOException {
        response.reset();
        response.getWriter().write("");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
