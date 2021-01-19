/*
 * Copyright (C) 2021 Asseco Central Europe, a.s. All rights reserved.
 *
 */

package com.asseco.ce.jtsr_digi.common.http.filter;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Slf4jMDC filter configuration.
 *
 * @author fukna
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "slf4jfilter")
@Slf4j
public class Slf4jMDCFilterConfiguration {

    private List<String> requestHeader;

    @Bean
    public FilterRegistrationBean<Slf4jMDCFilter> servletRegistrationBean() {
        if (log.isDebugEnabled()) {
            log.info("servletRegistrationBean() - requestHeader={}", requestHeader);
        }

        Slf4jMDCFilter filter = new Slf4jMDCFilter();
        filter.setHeaderNames(requestHeader);

        FilterRegistrationBean<Slf4jMDCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
