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

package com.guoanjia.common.credit.business.service.impl;

import com.alipay.api.response.ZhimaCustomerCertificationCertifyResponse;
import com.alipay.api.response.ZhimaCustomerCertificationInitializeResponse;
import com.alipay.api.response.ZhimaCustomerCertificationQueryResponse;
import com.guoanjia.common.credit.business.model.result.certification.CertificationCertifyResult;
import com.guoanjia.common.credit.business.model.result.certification.CertificationInitializeResult;
import com.guoanjia.common.credit.business.model.result.certification.CertificationQueryResult;
import com.guoanjia.common.credit.business.service.AliCertificationService;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationCertifyRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationInitializeRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationQueryRequest;
import com.guoanjia.common.credit.function.model.result.certification.AliCertificationCertifyResult;
import com.guoanjia.common.credit.function.model.result.certification.AliCertificationInitializeResult;
import com.guoanjia.common.credit.function.model.result.certification.AliCertificationQueryResult;
import com.guoanjia.common.credit.function.service.AliCertificationFunction;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import com.guoanjia.common.credit.utils.GlobalConstant;
import com.guoanjia.common.credit.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * @author AsherLi
 * @version 1.0.00
 */
@Service
public class AliCertificationServiceImpl implements AliCertificationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AliCertificationServiceImpl.class);

    private final AliCertificationFunction creditFunction;

    public AliCertificationServiceImpl(final AliCertificationFunction creditFunction) {
        this.creditFunction = creditFunction;
    }

    @Override
    public CertificationInitializeResult certificationInitialize(AliCertificationInitializeRequest request) {
        final AliCertificationInitializeResult result = creditFunction.certificationInitialize(request);
        final GlobalConstant.ExecutionStatus status = result.getExecutionStatus();
        final ZhimaCustomerCertificationInitializeResponse response = result.getResponse();
        switch (status) {
            case SUCCESS:
                String resultJson = BaseGsonBuilder.create().toJson(response);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("芝麻认证初始化接口调用成功，返回数据：\n{}", JsonUtil.format(resultJson));
                }
                return BaseGsonBuilder.create().fromJson(resultJson, CertificationInitializeResult.class);
            case FAILED:
                String errMsg = MessageFormat.format(
                        "芝麻认证初始化接口异常，" +
                                "系统异常代码：{0}，" +
                                "系统异常原因：{1}，" +
                                "异常详细编码：{2}，" +
                                "异常详细原因：{3}",
                        response.getCode(),
                        response.getMsg(),
                        response.getSubCode(),
                        response.getSubMsg()
                );

                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error(errMsg);
                }
                throw new RuntimeException(errMsg);
            case UNKNOWN:
            default:
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("芝麻认证初始化接口调用异常");
                }
                throw new RuntimeException("接口调用异常");
        }
    }

    @Override
    public CertificationCertifyResult certificationCertify(AliCertificationCertifyRequest request) {
        final AliCertificationCertifyResult result = creditFunction.certificationCertify(request);
        final GlobalConstant.ExecutionStatus status = result.getExecutionStatus();
        final ZhimaCustomerCertificationCertifyResponse response = result.getResponse();
        switch (status) {
            case SUCCESS:
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("芝麻认证接口调用成功，认证URL：{}", JsonUtil.format(BaseGsonBuilder.create().toJson(response)));
                }
                return new CertificationCertifyResult().setUrl(response.getBody()).setBizNo(response.getBizNo());
            case FAILED:
                String errMsg = MessageFormat.format(
                        "芝麻认证接口异常，" +
                                "系统异常代码：{0}，" +
                                "系统异常原因：{1}，" +
                                "异常详细编码：{2}，" +
                                "异常详细原因：{3}",
                        response.getCode(),
                        response.getMsg(),
                        response.getSubCode(),
                        response.getSubMsg()
                );
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error(errMsg);
                }
                throw new RuntimeException(errMsg);
            case UNKNOWN:
            default:
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("芝麻认证接口调用异常");
                }
                throw new RuntimeException("接口调用异常");
        }
    }

    @Override
    public CertificationQueryResult certificationQuery(AliCertificationQueryRequest request) {
        final AliCertificationQueryResult result = creditFunction.certificationQuery(request);
        final GlobalConstant.ExecutionStatus status = result.getExecutionStatus();
        final ZhimaCustomerCertificationQueryResponse response = result.getResponse();
        switch (status) {
            case SUCCESS:
                String resultJson = BaseGsonBuilder.create().toJson(response);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("芝麻认证接查询口调用成功，返回数据：\n{}", JsonUtil.format(resultJson));
                }
                return BaseGsonBuilder.create().fromJson(resultJson, CertificationQueryResult.class);
            case FAILED:
                String errMsg = MessageFormat.format(
                        "芝麻认证接查询口异常，" +
                                "系统异常代码：{0}，" +
                                "系统异常原因：{1}，" +
                                "异常详细编码：{2}，" +
                                "异常详细原因：{3}",
                        response.getCode(),
                        response.getMsg(),
                        response.getSubCode(),
                        response.getSubMsg()
                );

                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error(errMsg);
                }
                throw new RuntimeException(errMsg);
            case UNKNOWN:
            default:
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("芝麻认证查询接口调用异常");
                }
                throw new RuntimeException("接口调用异常");
        }
    }
}
