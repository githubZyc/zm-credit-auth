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


import com.guoanjia.common.credit.function.model.request.credit.AliCreditScoreBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliCreditWatchlistBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliGetUserCreditScoreRequest;
import com.guoanjia.common.credit.function.model.result.credit.AliCreditScoreBriefResult;
import com.guoanjia.common.credit.function.model.result.credit.AliCreditWatchlistBriefResult;
import com.guoanjia.common.credit.function.model.result.credit.AliGetUserCreditScoreResult;

import javax.validation.Valid;

/**
 * 芝麻信用功能模块
 *
 * @author AsherLi
 * @version 1.0.00
 */
public interface AliCreditFunction {
    /**
     * 芝麻信用评分普惠版
     * 用于商户做准入判断 商户输入准入分 判断用户是否准入
     *
     * @param request 请求参数
     * @return 返回是否准入
     */
    AliCreditScoreBriefResult creditScoreBrief(AliCreditScoreBriefRequest request);

    /**
     * 芝麻行业关注名单普惠版
     * 用于商户准入判断， 无需授权，商户亦可用于参考用户风险等级
     *
     * @param request 请求参数
     * @return 用户是否黑名单
     */
    AliCreditWatchlistBriefResult creditWatchlistBrief(AliCreditWatchlistBriefRequest request);

    /**
     * 查询芝麻用户的芝麻信用评分
     * @param request
     * @return
     */
    AliGetUserCreditScoreResult getUserCreditScore(AliGetUserCreditScoreRequest request);
}
