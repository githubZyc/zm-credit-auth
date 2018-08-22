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

package com.guoanjia.common.credit.function.service;


import com.guoanjia.common.credit.function.model.request.data.AliDataCloseloopUploadRequest;
import com.guoanjia.common.credit.function.model.request.data.AliDataSingleUploadRequest;
import com.guoanjia.common.credit.function.model.request.data.AliDataUploadInitializeRequest;
import com.guoanjia.common.credit.function.model.result.data.AliDataCloseloopUploadResult;
import com.guoanjia.common.credit.function.model.result.data.AliDataSingleUploadResult;
import com.guoanjia.common.credit.function.model.result.data.AliDataUploadInitializeResult;

/**
 * 数据类功能模块
 *
 * @author AsherLi
 * @version 1.0.00
 */
public interface AliDataFunction {

    /**
     * 芝麻数据上传初始化
     *
     * @param request 请求参数
     * @return 模板下载URL
     */
    AliDataUploadInitializeResult dataUploadInitialize(AliDataUploadInitializeRequest request);

    /**
     * 单条数据上传
     *
     * @param request 请求参数
     * @return 上传结果
     */
    AliDataSingleUploadResult dataSingleUpload(AliDataSingleUploadRequest request);

    /**
     * 批量数据上传
     *
     * @param request 请求参数
     * @return 上传结果
     */
    AliDataCloseloopUploadResult dataCloseloopUpload(AliDataCloseloopUploadRequest request);
}
