/*
 * Copyright (C) 2021 Asseco Central Europe, a.s. All rights reserved.
 *
 */

package com.asseco.ce.jtsr_digi.common.http.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Slf4jMDC filter.
 *
 * @author fukna
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class Slf4jMDCFilter extends OncePerRequestFilter {

    private List<String> headerNames;

    /**
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            headerNames.stream()
                .filter(name -> StringUtils.hasText(name))
                .filter(name -> StringUtils.hasText(request.getHeader(name)))
                .forEach(name -> MDC.put(name, request.getHeader(name)));

            filterChain.doFilter(request, response);

        } finally {
            headerNames.forEach(name -> MDC.remove(name));
        }
    }

}
