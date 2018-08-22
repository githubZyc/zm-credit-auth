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
import com.alipay.api.request.ZhimaCreditScoreBriefGetRequest;
import com.alipay.api.request.ZhimaCreditScoreGetRequest;
import com.alipay.api.request.ZhimaCreditWatchlistBriefGetRequest;
import com.alipay.api.response.ZhimaCreditScoreBriefGetResponse;
import com.alipay.api.response.ZhimaCreditScoreGetResponse;
import com.alipay.api.response.ZhimaCreditWatchlistBriefGetResponse;
import com.guoanjia.common.credit.function.config.AliConfig;
import com.guoanjia.common.credit.function.model.request.credit.AliCreditScoreBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliCreditWatchlistBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliGetUserCreditScoreRequest;
import com.guoanjia.common.credit.function.model.result.credit.AliCreditScoreBriefResult;
import com.guoanjia.common.credit.function.model.result.credit.AliCreditWatchlistBriefResult;
import com.guoanjia.common.credit.function.model.result.credit.AliGetUserCreditScoreResult;
import com.guoanjia.common.credit.function.service.AliCreditFunction;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import com.guoanjia.common.credit.utils.JsonUtil;

import static com.guoanjia.common.credit.utils.GlobalConstant.APP_OAUTH_TOKEN_KEY;

/**
 * 芝麻信用功能模块实现
 *
 * @author AsherLi
 * @version 1.0.00
 */
public class AliCreditFunctionImpl extends AbstractAliFunction implements AliCreditFunction {

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
    public AliCreditFunctionImpl initAliClient(AliConfig config) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("芝麻信用，初始化相关参数：\n{}", JsonUtil.format(BaseGsonBuilder.create().toJson(config)));
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
                LOGGER.debug("芝麻信用能模块初始化成功");
            }
            return this;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("芝麻信用，初始化失败.", e);
            }
            throw new RuntimeException("芝麻信用功能模块初始化失败");
        }
    }

    /**
     * 芝麻信用评分普惠版
     * 用于商户做准入判断 商户输入准入分 判断用户是否准入
     *
     * @param request 请求参数
     * @return 返回是否准入
     */
    @Override
    public AliCreditScoreBriefResult creditScoreBrief(AliCreditScoreBriefRequest request) {
        super.validateBuilder(request);
        //创建请求体
        final ZhimaCreditScoreBriefGetRequest aliRequest = new ZhimaCreditScoreBriefGetRequest();
        //设置平台参数
        aliRequest.putOtherTextParam(APP_OAUTH_TOKEN_KEY, request.getAppAuthToken());
        //设置业务参数
        aliRequest.setBizContent(request.getBizContent());
        //执行请求
        final ZhimaCreditScoreBriefGetResponse response = (ZhimaCreditScoreBriefGetResponse) getResponse(client, aliRequest);
        //请求结果封装
        final AliCreditScoreBriefResult result = new AliCreditScoreBriefResult(response);
        return result.setExecutionStatus(checkExecutionStatus(response));
    }

    /**
     * 芝麻行业关注名单普惠版
     * 用于商户准入判断， 无需授权，商户亦可用于参考用户风险等级
     *
     * @param request 请求参数
     * @return 用户是否黑名单
     */
    @Override
    public AliCreditWatchlistBriefResult creditWatchlistBrief(AliCreditWatchlistBriefRequest request) {
        super.validateBuilder(request);
        //创建请求体
        final ZhimaCreditWatchlistBriefGetRequest aliRequest = new ZhimaCreditWatchlistBriefGetRequest();
        //设置平台参数
        aliRequest.putOtherTextParam(APP_OAUTH_TOKEN_KEY, request.getAppAuthToken());
        //设置业务参数
        aliRequest.setBizContent(request.getBizContent());
        //执行请求
        final ZhimaCreditWatchlistBriefGetResponse response = (ZhimaCreditWatchlistBriefGetResponse) getResponse(client, aliRequest);
        //请求结果封装
        final AliCreditWatchlistBriefResult result = new AliCreditWatchlistBriefResult(response);
        return result.setExecutionStatus(checkExecutionStatus(response));
    }

    @Override
    public AliGetUserCreditScoreResult getUserCreditScore(AliGetUserCreditScoreRequest request) {
        super.validateBuilder(request);
        //创建请求体
        final ZhimaCreditScoreGetRequest aliRequest = new ZhimaCreditScoreGetRequest();
        //设置平台参数
        aliRequest.putOtherTextParam(APP_OAUTH_TOKEN_KEY, request.getAppAuthToken());
        //设置业务参数
        aliRequest.setBizContent(request.getBizContent());
        //执行请求
        final ZhimaCreditScoreGetResponse  response = (ZhimaCreditScoreGetResponse) getResponse(client, aliRequest);
        //请求结果封装
        AliGetUserCreditScoreResult aliGetUserCreditScoreResult = new AliGetUserCreditScoreResult(response);
        return aliGetUserCreditScoreResult.setExecutionStatus(checkExecutionStatus(response));
    }
}
