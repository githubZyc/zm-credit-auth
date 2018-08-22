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


import com.guoanjia.common.credit.business.model.result.credit.CreditGetUserCreditScoreResult;
import com.guoanjia.common.credit.business.model.result.credit.CreditScoreBriefResult;
import com.guoanjia.common.credit.business.model.result.credit.CreditWatchlistBriefResult;
import com.guoanjia.common.credit.business.service.AliCreditService;
import com.guoanjia.common.credit.config.model.Result;
import com.guoanjia.common.credit.function.model.request.credit.AliCreditScoreBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliCreditWatchlistBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliGetUserCreditScoreRequest;
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
 * 芝麻信用普惠版
 *
 * @author AsherLi
 * @version 1.0.00
 */
@Api(description = "芝麻信用普惠版")
@RestController
@RequestMapping(path = "credit",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AliCreditController {

    private final AliCreditService aliCreditService;

    public AliCreditController(final AliCreditService aliCreditService) {
        this.aliCreditService = aliCreditService;
    }

    /**
     * 芝麻信用评分普惠版
     *
     * @param request 芝麻信用评分普惠版请求数据
     * @return 返回数据
     */
    @ApiOperation(value = "芝麻信用评分普惠版", notes = "判断用户信用评分是否满足要求")
    @ApiImplicitParam(value = "芝麻信用评分普惠版请求数据", required = true, name = "request", dataType = "AliCreditScoreBriefRequest")
    @PostMapping(path = "creditScoreBrief")
    public Result<CreditScoreBriefResult> creditScoreBrief(@RequestBody @Valid AliCreditScoreBriefRequest request) {
        final CreditScoreBriefResult result = this.aliCreditService.creditScoreBrief(request);
        return new Result<>(result);
    }

    /**
     * 行业关注名单普惠版
     *
     * @param request 芝麻信用行业关注名单普惠版请求数据
     * @return 返回数据
     */
    @ApiOperation(value = "芝麻信用行业关注名单普惠版", notes = "判断用户风险等级")
    @ApiImplicitParam(value = "芝麻信用行业关注名单普惠版请求数据", required = true, name = "request", dataType = "AliCreditWatchlistBriefRequest")
    @PostMapping(path = "watchlistBrief")
    public Result<CreditWatchlistBriefResult> watchlistBrief(@RequestBody @Valid AliCreditWatchlistBriefRequest request) {
        final CreditWatchlistBriefResult result = this.aliCreditService.creditWatchlistBrief(request);
        return new Result<>(result);
    }

    /**
     * 获取个人芝麻信用评分
     *
     * @param request 查询芝麻用户的芝麻信用评分
     * @return 返回数据
     */
    @ApiOperation(value = "查询芝麻用户的芝麻信用评分", notes = "获取个人芝麻信用评分")
    @ApiImplicitParam(value = "查询芝麻用户的芝麻信用评分请求数据", required = true, name = "request", dataType = "AliGetUserCreditScoreRequest")
    @PostMapping(path = "getUserCreditScore")
    public Result<CreditGetUserCreditScoreResult> getUserCreditScore(@RequestBody @Valid AliGetUserCreditScoreRequest request) {
        final CreditGetUserCreditScoreResult result = this.aliCreditService.getUserCreditScore(request);
        return new Result<>(result);
    }

}
