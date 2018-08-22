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
import com.alipay.api.FileItem;
import com.alipay.api.request.ZhimaMerchantCloseloopDataUploadRequest;
import com.alipay.api.request.ZhimaMerchantDataUploadInitializeRequest;
import com.alipay.api.request.ZhimaMerchantSingleDataUploadRequest;
import com.alipay.api.response.ZhimaMerchantCloseloopDataUploadResponse;
import com.alipay.api.response.ZhimaMerchantDataUploadInitializeResponse;
import com.alipay.api.response.ZhimaMerchantSingleDataUploadResponse;
import com.guoanjia.common.credit.function.config.AliConfig;
import com.guoanjia.common.credit.function.model.request.data.AliDataCloseloopUploadRequest;
import com.guoanjia.common.credit.function.model.request.data.AliDataSingleUploadRequest;
import com.guoanjia.common.credit.function.model.request.data.AliDataUploadInitializeRequest;
import com.guoanjia.common.credit.function.model.result.data.AliDataCloseloopUploadResult;
import com.guoanjia.common.credit.function.model.result.data.AliDataSingleUploadResult;
import com.guoanjia.common.credit.function.model.result.data.AliDataUploadInitializeResult;
import com.guoanjia.common.credit.function.service.AliDataFunction;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import com.guoanjia.common.credit.utils.JsonUtil;

import static com.guoanjia.common.credit.utils.GlobalConstant.APP_OAUTH_TOKEN_KEY;

/**
 * 阿里数据类功能模块实现
 *
 * @author AsherLi
 * @version 1.0.00
 */
public class AliDataFunctionImpl extends AbstractAliFunction implements AliDataFunction {

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
    public AliDataFunctionImpl initAliClient(AliConfig config) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("数据类功能模块初始化，初始化相关参数：\n{}", JsonUtil.format(BaseGsonBuilder.create().toJson(config)));
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
                LOGGER.debug("数据类功能模块初始化成功");
            }
            return this;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("数据类功能模块初始化失败.", 5);
            }
            throw new RuntimeException("数据类功能模块初始化失败");
        }
    }

    /**
     * 芝麻数据上传初始化
     *
     * @param request 请求参数
     * @return 模板下载URL
     */
    @Override
    public AliDataUploadInitializeResult dataUploadInitialize(AliDataUploadInitializeRequest request) {
        super.validateBuilder(request);
        //创建请求体
        final ZhimaMerchantDataUploadInitializeRequest aliRequest = new ZhimaMerchantDataUploadInitializeRequest();
        // 设置平台参数
        aliRequest.putOtherTextParam(APP_OAUTH_TOKEN_KEY, request.getAppAuthToken());
        // 设置业务参数
        aliRequest.setBizContent(request.getBizContent());
        //执行请求
        final ZhimaMerchantDataUploadInitializeResponse response = (ZhimaMerchantDataUploadInitializeResponse) getResponse(client, aliRequest);
        //请求结果封装
        final AliDataUploadInitializeResult result = new AliDataUploadInitializeResult(response);
        return result.setExecutionStatus(checkExecutionStatus(response));
    }

    /**
     * 单条数据上传
     *
     * @param request 请求参数
     * @return 上传结果
     */
    @Override
    public AliDataSingleUploadResult dataSingleUpload(AliDataSingleUploadRequest request) {
        super.validateBuilder(request);
        //创建请求体
        final ZhimaMerchantSingleDataUploadRequest aliRequest = new ZhimaMerchantSingleDataUploadRequest();
        // 设置平台参数
        aliRequest.putOtherTextParam(APP_OAUTH_TOKEN_KEY, request.getAppAuthToken());
        // 设置业务参数
        aliRequest.setBizContent(request.getBizContent());
        //执行请求
        final ZhimaMerchantSingleDataUploadResponse response = (ZhimaMerchantSingleDataUploadResponse) getResponse(client, aliRequest);
        //请求结果封装
        final AliDataSingleUploadResult result = new AliDataSingleUploadResult(response);
        return result.setExecutionStatus(checkExecutionStatus(response));
    }

    /**
     * 批量数据上传
     *
     * @param request 请求参数
     * @return 上传结果
     */
    @Override
    public AliDataCloseloopUploadResult dataCloseloopUpload(AliDataCloseloopUploadRequest request) {
        super.validateBuilder(request);
        //创建请求体
        final ZhimaMerchantCloseloopDataUploadRequest aliRequest = new ZhimaMerchantCloseloopDataUploadRequest();
        // 设置平台参数
        aliRequest.putOtherTextParam(APP_OAUTH_TOKEN_KEY, request.getAppAuthToken());
        // 设置业务参数
        aliRequest.setFileCharset(request.getFileCharset());
        aliRequest.setRecords(request.getRecords());
        aliRequest.setPrimaryKeyColumns(request.getPrimaryKeyColumns());
        aliRequest.setBizExtParams(request.getBizExtParams());
        aliRequest.setFile(new FileItem(request.getFile()));
        aliRequest.setSceneCode(request.getSceneCode());
        aliRequest.setColumns(request.getColumns());
        //执行请求
        final ZhimaMerchantCloseloopDataUploadResponse response = (ZhimaMerchantCloseloopDataUploadResponse) getResponse(client, aliRequest);
        //请求结果封装
        final AliDataCloseloopUploadResult result = new AliDataCloseloopUploadResult(response);
        return result.setExecutionStatus(checkExecutionStatus(response));
    }


}
