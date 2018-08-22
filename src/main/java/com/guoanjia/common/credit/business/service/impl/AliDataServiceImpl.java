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

import com.alipay.api.response.ZhimaMerchantCloseloopDataUploadResponse;
import com.alipay.api.response.ZhimaMerchantDataUploadInitializeResponse;
import com.alipay.api.response.ZhimaMerchantSingleDataUploadResponse;
import com.guoanjia.common.credit.business.model.result.data.DataUploadInitializeResult;
import com.guoanjia.common.credit.business.model.result.data.DataUploadResult;
import com.guoanjia.common.credit.business.service.AliDataService;
import com.guoanjia.common.credit.function.model.request.data.AliDataCloseloopUploadRequest;
import com.guoanjia.common.credit.function.model.request.data.AliDataSingleUploadRequest;
import com.guoanjia.common.credit.function.model.request.data.AliDataUploadInitializeRequest;
import com.guoanjia.common.credit.function.model.result.data.AliDataCloseloopUploadResult;
import com.guoanjia.common.credit.function.model.result.data.AliDataSingleUploadResult;
import com.guoanjia.common.credit.function.model.result.data.AliDataUploadInitializeResult;
import com.guoanjia.common.credit.function.service.AliDataFunction;
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
public class AliDataServiceImpl implements AliDataService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AliDataServiceImpl.class);

    private final AliDataFunction dataFunction;

    public AliDataServiceImpl(final AliDataFunction dataFunction) {
        this.dataFunction = dataFunction;
    }

    @Override
    public DataUploadInitializeResult dataUploadInitialize(AliDataUploadInitializeRequest request) {
        final AliDataUploadInitializeResult result = this.dataFunction.dataUploadInitialize(request);
        final GlobalConstant.ExecutionStatus status = result.getExecutionStatus();
        final ZhimaMerchantDataUploadInitializeResponse response = result.getResponse();
        switch (status) {
            case SUCCESS:
                String resultJson = BaseGsonBuilder.create().toJson(response);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("芝麻数据传入初始化接口调用成功：\n{}", JsonUtil.format(resultJson));
                }
                return BaseGsonBuilder.create().fromJson(resultJson, DataUploadInitializeResult.class);
            case FAILED:
                final String errMsg = MessageFormat.format(
                        "芝麻数据传入初始化接口异常，" +
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
                    LOGGER.error("芝麻数据传入初始化接口调用异常");
                }
                throw new RuntimeException("芝麻数据传入初始化接口调用异常");
        }
    }

    @Override
    public DataUploadResult dataSingleUpload(AliDataSingleUploadRequest request) {
        final AliDataSingleUploadResult result = this.dataFunction.dataSingleUpload(request);
        final GlobalConstant.ExecutionStatus status = result.getExecutionStatus();
        final ZhimaMerchantSingleDataUploadResponse response = result.getResponse();
        switch (status) {
            case SUCCESS:
                String resultJson = BaseGsonBuilder.create().toJson(response);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("芝麻单条数据传入接口调用成功：\n{}", JsonUtil.format(resultJson));
                }
                return BaseGsonBuilder.create().fromJson(resultJson, DataUploadResult.class);
            case FAILED:
                final String errMsg = MessageFormat.format(
                        "芝麻单条数据传入接口异常，" +
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
                    LOGGER.error("芝麻单条数据传入接口调用异常");
                }
                throw new RuntimeException("芝麻单条数据传入接口调用异常");
        }
    }

    @Override
    public DataUploadResult dataCloseloopUpload(AliDataCloseloopUploadRequest request) {
        final AliDataCloseloopUploadResult result = this.dataFunction.dataCloseloopUpload(request);
        final GlobalConstant.ExecutionStatus status = result.getExecutionStatus();
        final ZhimaMerchantCloseloopDataUploadResponse response = result.getResponse();
        switch (status) {
            case SUCCESS:
                String resultJson = BaseGsonBuilder.create().toJson(response);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("芝麻数据批量传入接口调用成功：\n{}", JsonUtil.format(resultJson));
                }
                return BaseGsonBuilder.create().fromJson(resultJson, DataUploadResult.class);
            case FAILED:
                final String errMsg = MessageFormat.format(
                        "芝麻批量数据传入接口异常，" +
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
                    LOGGER.error("芝麻数据批量传入接口调用异常");
                }
                throw new RuntimeException("芝麻数据批量传入接口调用异常");
        }

    }
}
