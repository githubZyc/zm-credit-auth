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

package com.guoanjia.common.credit.business.controller;


import com.guoanjia.common.credit.business.model.result.data.DataUploadInitializeResult;
import com.guoanjia.common.credit.business.model.result.data.DataUploadResult;
import com.guoanjia.common.credit.business.service.AliDataService;
import com.guoanjia.common.credit.config.model.Result;
import com.guoanjia.common.credit.function.model.request.data.AliDataCloseloopUploadRequest;
import com.guoanjia.common.credit.function.model.request.data.AliDataSingleUploadRequest;
import com.guoanjia.common.credit.function.model.request.data.AliDataUploadInitializeRequest;
import com.guoanjia.common.credit.utils.DataFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author AsherLi
 * @version 1.0.00
 */
@Api(description = "芝麻信用数据反馈")
@RestController
@RequestMapping(path = "data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AliDataController {

    private final AliDataService aliDataService;

    public AliDataController(final AliDataService aliDataService) {
        this.aliDataService = aliDataService;
    }

    @ApiOperation(value = "芝麻数据传入初始化", notes = "数据传入前初始化，主要包括：配置通道，查询场景模板的url地址")
    @ApiImplicitParam(value = "芝麻数据传入初始化请求数据", required = true, name = "request", dataType = "AliDataUploadInitializeRequest")
    @PostMapping(path = "dataUploadInitialize")
    public Result<DataUploadInitializeResult> dataUploadInitialize(@RequestBody @Valid AliDataUploadInitializeRequest request) {
        final DataUploadInitializeResult result = aliDataService.dataUploadInitialize(request);
        return new Result<>(result);
    }

    @ApiOperation(value = "单条数据传入", notes = "单条数据传入")
    @ApiImplicitParam(value = "单条数据传入请求数据", required = true, name = "request", dataType = "AliDataSingleUploadRequest")
    @PostMapping(path = "dataSingleUpload")
    public Result<DataUploadResult> dataSingleUpload(@RequestBody @Valid AliDataSingleUploadRequest request) {
        final DataUploadResult result = aliDataService.dataSingleUpload(request);
        return new Result<>(result);
    }

    @ApiOperation(value = "批量数据传入", notes = "商户通过该接口把数据以json文件形式批量传入给芝麻信用，接口最大支持50M的单文件，对于超过50M的数据文件，需要拆分为多个独立的json文件进行传入，确保单json文件小于50M")
    @ApiImplicitParams(value = {
//            @ApiImplicitParam(value = "批量数据传入请求数据", required = true, name = "request", dataType = "AliDataCloseloopUploadRequest",dataTypeClass = AliDataCloseloopUploadRequest.class,paramType = "form",readOnly = true),
            @ApiImplicitParam(value = "信用反馈数据文件", required = true, name = "uploadFile", dataType = "MultipartFile")
    })
    @PostMapping(path = "dataCloseloopUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<DataUploadResult> dataCloseloopUpload(@Valid AliDataCloseloopUploadRequest request, @RequestParam("uploadFile") MultipartFile uploadFile) {
        request.setFile(DataFileUtils.createTempFile(uploadFile));
        final DataUploadResult result = aliDataService.dataCloseloopUpload(request);
        return new Result<>(result);
    }

}
