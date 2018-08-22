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

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.ZhimaCustomerCertificationCertifyRequest;
import com.alipay.api.request.ZhimaCustomerCertificationInitializeRequest;
import com.alipay.api.request.ZhimaCustomerCertificationQueryRequest;
import com.alipay.api.response.ZhimaCustomerCertificationCertifyResponse;
import com.alipay.api.response.ZhimaCustomerCertificationInitializeResponse;
import com.alipay.api.response.ZhimaCustomerCertificationQueryResponse;
import com.guoanjia.common.credit.function.config.AliConfig;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationCertifyRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationInitializeRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationQueryRequest;
import com.guoanjia.common.credit.function.model.result.certification.AliCertificationCertifyResult;
import com.guoanjia.common.credit.function.model.result.certification.AliCertificationInitializeResult;
import com.guoanjia.common.credit.function.model.result.certification.AliCertificationQueryResult;
import com.guoanjia.common.credit.function.service.AliCertificationFunction;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import com.guoanjia.common.credit.utils.JsonUtil;

import static com.guoanjia.common.credit.utils.GlobalConstant.APP_OAUTH_TOKEN_KEY;
import static com.guoanjia.common.credit.utils.GlobalConstant.AliProductCode.CERTIFICATION_INITIALIZE;


/**
 * 芝麻认证功能模块实现
 *
 * @author AsherLi
 * @version 1.0.00
 */
public class AliCertificationFunctionImpl extends AbstractAliFunction implements AliCertificationFunction {

    /**
     * 请求执行器
     */
    private AlipayClient client;

    /**
     * 初始化请求执行器
     *
     * @param config 配置参数
     * @return 实例化
     */
    public AliCertificationFunctionImpl initAliClient(AliConfig config) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("芝麻认证，初始化相关参数：\n{}", JsonUtil.format(BaseGsonBuilder.create().toJson(config)));
        }
        try {
            this.client = new DefaultAlipayClient(
                    config.getGatewayUrl(),
                    config.getAppId(),
                    config.getPrivateKey(),
                    config.getFormat(),
                    config.getCharset(),
                    config.getAliPublicKey(),
                    config.getSignType()
            );
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("芝麻认证功能模块初始化成功");
            }
            return this;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("芝麻认证，初始化失败.", e);
            }
            throw new RuntimeException("芝麻认证功能模块初始化失败");
        }
    }

    /**
     * 芝麻认证开始认证
     *
     * @param request 请求参数
     * @return 认证结果
     */
    @Override
    public AliCertificationCertifyResult certificationCertify(AliCertificationCertifyRequest request) {
        super.validateBuilder(request);
        //创建请求体
        final ZhimaCustomerCertificationCertifyRequest aliRequest = new ZhimaCustomerCertificationCertifyRequest();
        //设置平台参数
        aliRequest.putOtherTextParam(APP_OAUTH_TOKEN_KEY, request.getAppAuthToken());
        //设置业务参数
        aliRequest.setBizContent(request.getBizContent());
        aliRequest.setReturnUrl(request.getReturnUrl());
        //执行请求
        final ZhimaCustomerCertificationCertifyResponse response = (ZhimaCustomerCertificationCertifyResponse) getPageResponse(client, aliRequest, "GET");
        //请求结果封装
        final AliCertificationCertifyResult result = new AliCertificationCertifyResult(response);
        return result.setExecutionStatus(checkPageExecutionStatus(response));
    }

    /**
     * 芝麻认证初始化
     *
     * @param request 请求参数
     * @return 认证唯一标识
     */
    @Override
    public AliCertificationInitializeResult certificationInitialize(AliCertificationInitializeRequest request) {
        super.validateBuilder(request);
        //创建请求体
        final ZhimaCustomerCertificationInitializeRequest aliRequest = new ZhimaCustomerCertificationInitializeRequest();
        //设置平台参数
        aliRequest.putOtherTextParam(APP_OAUTH_TOKEN_KEY, request.getAppAuthToken());
        //设置业务参数
        aliRequest.setBizContent(request.getBizContent());
        //执行请求
        final ZhimaCustomerCertificationInitializeResponse response = (ZhimaCustomerCertificationInitializeResponse) getResponse(client, aliRequest);
        //请求结果封装
        final AliCertificationInitializeResult result = new AliCertificationInitializeResult(response);
        return result.setExecutionStatus(checkExecutionStatus(response));
    }

    /**
     * 芝麻认证查询
     *
     * @param request 请求参数
     * @return 认证结果
     */
    @Override
    public AliCertificationQueryResult certificationQuery(AliCertificationQueryRequest request) {
        super.validateBuilder(request);
        //创建请求体
        final ZhimaCustomerCertificationQueryRequest aliRequest = new ZhimaCustomerCertificationQueryRequest();
        //设置平台参数
        aliRequest.putOtherTextParam(APP_OAUTH_TOKEN_KEY, request.getAppAuthToken());
        //设置业务参数
        aliRequest.setBizContent(request.getBizContent());
        //执行请求
        final ZhimaCustomerCertificationQueryResponse response = (ZhimaCustomerCertificationQueryResponse) getResponse(client, aliRequest);
        //请求结果封装
        final AliCertificationQueryResult result = new AliCertificationQueryResult(response);
        return result.setExecutionStatus(checkExecutionStatus(response));
    }

}
