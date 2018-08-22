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

import com.alipay.api.response.ZhimaCreditScoreBriefGetResponse;
import com.alipay.api.response.ZhimaCreditScoreGetResponse;
import com.alipay.api.response.ZhimaCreditWatchlistBriefGetResponse;
import com.guoanjia.common.credit.business.model.result.credit.CreditGetUserCreditScoreResult;
import com.guoanjia.common.credit.business.model.result.credit.CreditScoreBriefResult;
import com.guoanjia.common.credit.business.model.result.credit.CreditWatchlistBriefResult;
import com.guoanjia.common.credit.business.service.AliCreditService;
import com.guoanjia.common.credit.function.model.request.credit.AliCreditScoreBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliCreditWatchlistBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliGetUserCreditScoreRequest;
import com.guoanjia.common.credit.function.model.result.credit.AliCreditScoreBriefResult;
import com.guoanjia.common.credit.function.model.result.credit.AliCreditWatchlistBriefResult;
import com.guoanjia.common.credit.function.model.result.credit.AliGetUserCreditScoreResult;
import com.guoanjia.common.credit.function.service.AliCreditFunction;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import com.guoanjia.common.credit.utils.GlobalConstant;
import com.guoanjia.common.credit.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.MessageFormat;

/**
 * @author AsherLi
 * @version 1.0.00
 */
@Service
public class AliCreditServiceImpl implements AliCreditService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AliCreditServiceImpl.class);

    private final AliCreditFunction creditFunction;

    public AliCreditServiceImpl(final AliCreditFunction creditFunction) {
        this.creditFunction = creditFunction;
    }

    @Override
    public CreditScoreBriefResult creditScoreBrief(AliCreditScoreBriefRequest request) {
        final AliCreditScoreBriefResult result = creditFunction.creditScoreBrief(request);
        final GlobalConstant.ExecutionStatus status = result.getExecutionStatus();
        final ZhimaCreditScoreBriefGetResponse response = result.getResponse();
        switch (status) {
            case SUCCESS:
                String resultJson = BaseGsonBuilder.create().toJson(response);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("芝麻信用普惠版接口调用成功：\n{}", JsonUtil.format(resultJson));
                }
                return BaseGsonBuilder.create().fromJson(resultJson, CreditScoreBriefResult.class);
            case FAILED:
                final String errMsg = MessageFormat.format(
                        "芝麻信用普惠版接口异常，" +
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
                    LOGGER.error("芝麻信用普惠版接口调用异常");
                }
                throw new RuntimeException("接口调用异常");
        }
    }

    @Override
    public CreditWatchlistBriefResult creditWatchlistBrief(AliCreditWatchlistBriefRequest request) {
        final AliCreditWatchlistBriefResult result = creditFunction.creditWatchlistBrief(request);
        final GlobalConstant.ExecutionStatus status = result.getExecutionStatus();
        final ZhimaCreditWatchlistBriefGetResponse response = result.getResponse();
        switch (status) {
            case SUCCESS:
                String resultJson = BaseGsonBuilder.create().toJson(response);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("行业关注名单普惠版接口调用成功：\n{}", JsonUtil.format(resultJson));
                }
                return BaseGsonBuilder.create().fromJson(resultJson, CreditWatchlistBriefResult.class);
            case FAILED:
                final String errMsg = MessageFormat.format(
                        "行业关注名单普惠版接口异常，" +
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
                    LOGGER.error("行业关注名单普惠版接口调用异常");
                }
                throw new RuntimeException("接口调用异常");
        }
    }

    @Override
    public CreditGetUserCreditScoreResult getUserCreditScore(@Valid AliGetUserCreditScoreRequest request) {
        AliGetUserCreditScoreResult userCreditScoreResult = creditFunction.getUserCreditScore(request);
        final GlobalConstant.ExecutionStatus status = userCreditScoreResult.getExecutionStatus();
        final ZhimaCreditScoreGetResponse response = userCreditScoreResult.getResponse();
        switch (status) {
            case SUCCESS:
                String resultJson = BaseGsonBuilder.create().toJson(response);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("行业关注名单普惠版接口调用成功：\n{}", JsonUtil.format(resultJson));
                }
                return BaseGsonBuilder.create().fromJson(resultJson, CreditGetUserCreditScoreResult.class);
            case FAILED:
                final String errMsg = MessageFormat.format(
                        "查询芝麻用户的芝麻信用评分接口异常，" +
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
                    LOGGER.error("查询芝麻用户的芝麻信用评分接口调用异常");
                }
                throw new RuntimeException("接口调用异常");
        }
    }
}
