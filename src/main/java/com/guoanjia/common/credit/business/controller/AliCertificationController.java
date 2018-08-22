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


import com.guoanjia.common.credit.business.model.result.certification.CertificationCertifyResult;
import com.guoanjia.common.credit.business.model.result.certification.CertificationInitializeResult;
import com.guoanjia.common.credit.business.model.result.certification.CertificationQueryResult;
import com.guoanjia.common.credit.business.service.AliCertificationService;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationCertifyRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationInitializeRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationQueryRequest;
import com.guoanjia.common.credit.config.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 芝麻认证
 *
 * @author AsherLi
 * @version 1.0.00
 */
@Api(description = "芝麻认证")
@RestController
@RequestMapping(path = "certification",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AliCertificationController {

    private final AliCertificationService aliCertificationService;

    public AliCertificationController(final AliCertificationService aliCertificationService) {
        this.aliCertificationService = aliCertificationService;
    }

    /**
     * 芝麻认证初
     *
     * @param request 请求参数
     * @return 请求结果
     */
    @ApiOperation(value = "芝麻认证初", notes = "芝麻认证初")
    @ApiImplicitParam(value = "芝麻认证请求数据", required = true, name = "request", dataType = "AliCertificationInitializeRequest")
    @PostMapping(path = "certificationCertify")
    public Result<CertificationCertifyResult> certificationCertify(@RequestBody @Valid AliCertificationInitializeRequest request) {
        final CertificationInitializeResult initializeResult = this.aliCertificationService.certificationInitialize(request);
        AliCertificationCertifyRequest cRequest = new AliCertificationCertifyRequest();

        cRequest.setBizNo(initializeResult.getBizNo());
        cRequest.setReturnUrl(request.getReturnUrl());
        final CertificationCertifyResult result = this.aliCertificationService.certificationCertify(cRequest);
        result.setBizNo(initializeResult.getBizNo());
        return new Result<>(result);
    }

    /**
     * 芝麻认证查询
     *
     * @param request 请求参数
     * @return 请求结果
     */
    @ApiOperation(value = "芝麻认证查询", notes = "查询认证的状态和结果")
    @ApiImplicitParam(value = "芝麻认证查询请求数据", required = true, name = "request", dataType = "AliCertificationQueryRequest")
    @PostMapping(path = "certificationQuery")
    public Result<CertificationQueryResult> certificationQuery(@RequestBody @Valid AliCertificationQueryRequest request) {
        final CertificationQueryResult result = this.aliCertificationService.certificationQuery(request);
        return new Result<>(result);
    }
}
