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

package com.guoanjia.common.credit.function.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.guoanjia.common.credit.function.model.request.AbstractRequest;
import com.guoanjia.common.credit.utils.GlobalConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static com.guoanjia.common.credit.utils.GlobalConstant.ExecutionCode.SUCCESS;


/**
 * 阿里功能模块抽象基类
 *
 * @author AsherLi
 * @version 1.0.00
 */
abstract class AbstractAliFunction {

    static final Logger LOGGER = LoggerFactory.getLogger(AbstractAliFunction.class);


    /**
     * 验证请求参数
     *
     * @param request 请求参数
     */
    void validateBuilder(AbstractRequest request) {
        if (Objects.isNull(request)) {
            throw new NullPointerException("AbstractRequest 不能为 NULL");
        }
        //校验参数约束条件
        request.checkConstraints();
    }

    /**
     * 调用AlipayClient的execute方法，进行远程调用
     *
     * @param client  AlipayClient
     * @param request 请求数据
     * @return 返回结果
     */
    AlipayResponse getResponse(AlipayClient client, AlipayRequest<?> request) {
        try {
            final AlipayResponse alipayResponse = client.execute(request);
            if (Objects.nonNull(alipayResponse)) {
                return alipayResponse;
            }
            return null;
        } catch (AlipayApiException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("阿里请求失败，返回异常码：{}，返回异常原因：{}", e.getErrCode(), e.getErrMsg(), e);
            }
            return null;
        }
    }

    /**
     * 调用AlipayClient的execute方法，进行远程调用
     *
     * @param client     AlipayClient
     * @param authoToken 授权令牌
     * @param request    请求数据
     * @return 返回结果
     */
    AlipayResponse getResponse(AlipayClient client, AlipayRequest<?> request, String authoToken) {
        try {
            final AlipayResponse alipayResponse = client.execute(request, authoToken);
            if (Objects.nonNull(alipayResponse)) {
                return alipayResponse;
            }
            return null;
        } catch (AlipayApiException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("阿里请求失败，返回异常码：{}，返回异常原因：{}", e.getErrCode(), e.getErrMsg(), e);
            }
            return null;
        }
    }

    /**
     * 调用AlipayClient的execute方法，进行远程调用
     *
     * @param client      AlipayClient
     * @param accessToken 访问令牌
     * @param authoToken  授权令牌
     * @param request     请求数据
     * @return 返回结果
     */
    AlipayResponse getResponse(AlipayClient client, AlipayRequest<?> request, String accessToken, String authoToken) {
        try {
            final AlipayResponse alipayResponse = client.execute(request, accessToken, authoToken);
            if (Objects.nonNull(alipayResponse)) {
                return alipayResponse;
            }
            return null;
        } catch (AlipayApiException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("阿里请求失败，返回异常码：{}，返回异常原因：{}", e.getErrCode(), e.getErrMsg(), e);
            }
            return null;
        }
    }

    /**
     * 调用AlipayClient的pageExecute方法，进行远程调用
     *
     * @param client  AlipayClient
     * @param method 请求方法
     * @param request 请求数据
     * @return 返回结果
     */
    AlipayResponse getPageResponse(AlipayClient client, AlipayRequest<?> request,String method) {
        try {
            final AlipayResponse alipayResponse = client.pageExecute(request, method);
            if (Objects.nonNull(alipayResponse)) {
                return alipayResponse;
            }
            return null;
        } catch (AlipayApiException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("阿里请求失败，返回异常码：{}，返回异常原因：{}", e.getErrCode(), e.getErrMsg(), e);
            }
            return null;
        }
    }

    /**
     * 调用AlipayClient的pageExecute方法，进行远程调用
     *
     * @param client  AlipayClient
     * @param request 请求数据
     * @return 返回结果
     */
    AlipayResponse getPageResponse(AlipayClient client, AlipayRequest<?> request) {
        try {
            final AlipayResponse alipayResponse = client.pageExecute(request);
            if (Objects.nonNull(alipayResponse)) {
                return alipayResponse;
            }
            return null;
        } catch (AlipayApiException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("阿里请求失败，返回异常码：{}，返回异常原因：{}", e.getErrCode(), e.getErrMsg(), e);
            }
            return null;
        }
    }

    /**
     * 检查接口请求执行结果状态
     *
     * @param response 阿里返回结果数据
     * @return 执行状态
     */
    GlobalConstant.ExecutionStatus checkExecutionStatus(AlipayResponse response) {
        if (Objects.isNull(response)) {
            return GlobalConstant.ExecutionStatus.UNKNOWN;
        } else if (Objects.equals(SUCCESS, response.getCode())) {
            return GlobalConstant.ExecutionStatus.SUCCESS;
        } else {
            return GlobalConstant.ExecutionStatus.FAILED;
        }
    }

    /**
     * 检查页面接口请求执行结果状态
     *
     * @param response 阿里返回结果数据
     * @return 执行状态
     */
    GlobalConstant.ExecutionStatus checkPageExecutionStatus(AlipayResponse response) {
        if (Objects.isNull(response)) {
            return GlobalConstant.ExecutionStatus.UNKNOWN;
        } else if (StringUtils.isNotBlank(response.getBody())) {
            return GlobalConstant.ExecutionStatus.SUCCESS;
        } else {
            return GlobalConstant.ExecutionStatus.FAILED;
        }
    }


}
