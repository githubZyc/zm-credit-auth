/*
 * Copyright 2013-2018 Tibet CITIC Guoan Real Estate Project Management Co., Ltd. Beijing Branch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.guoanjia.common.credit.filter;//package com.guoanjia.smarthome.smartlock.filter;


import com.guoanjia.common.credit.filter.ext.wrapper.BufferedRequestWrapper;
import com.guoanjia.common.credit.filter.ext.wrapper.BufferedResponseWrapper;
import com.guoanjia.common.credit.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求过滤器
 *
 * @author AsherLi0103
 * @version 1.0.00
 */
@Slf4j
@WebFilter(filterName = "ACCESS_FILTER", urlPatterns = {"/*"})
public class AccessLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            Map<String, String> requestMap = this.getTypesafeRequestMap(httpServletRequest);
            BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper(httpServletRequest);
            BufferedResponseWrapper bufferedResponse = new BufferedResponseWrapper(httpServletResponse);

            String requestURI = bufferedRequest.getRequestURI();
            if (!requestURI.contains("swagger") && !requestURI.contains("webjars")&& !requestURI.contains("/v2/api-docs")) {
                final StringBuilder logMessage = new StringBuilder("REST Request - ");
                logMessage.append("\n[请求路径] ");
                logMessage.append(requestURI);
                logMessage.append("\n[请求方法] ");
                logMessage.append(bufferedRequest.getMethod());
                logMessage.append("\n[路径信息] ");
                logMessage.append(bufferedRequest.getPathInfo());
                logMessage.append("\n[请求参数] ");
                logMessage.append(requestMap);
                logMessage.append("\n[请 求 体] \n");
                logMessage.append(JsonUtil.format(bufferedRequest.getRequestBody()));
                logMessage.append("\n[请求地址] ");
                logMessage.append(bufferedRequest.getRemoteAddr());
                chain.doFilter(bufferedRequest, bufferedResponse);
                String responseContent = bufferedResponse.getContent();
                logMessage.append(JsonUtil.format(responseContent));
                log.error(logMessage.toString());
            }else{
                chain.doFilter(bufferedRequest, bufferedResponse);
            }

        } catch (Throwable a) {
            log.error(a.getLocalizedMessage());
        }
    }


    private Map<String, String> getTypesafeRequestMap(HttpServletRequest request) {
        Map<String, String> typesafeRequestMap = new HashMap<>();
        Enumeration<String> requestParamNames = request.getParameterNames();
        while (requestParamNames.hasMoreElements()) {
            String requestParamName = requestParamNames.nextElement();
            String requestParamValue = request.getParameter(requestParamName);
            typesafeRequestMap.put(requestParamName, requestParamValue);
        }
        return typesafeRequestMap;
    }


    @Override
    public void destroy() {
    }

}