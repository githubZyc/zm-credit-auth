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

package com.guoanjia.common.credit.business.service;


import com.guoanjia.common.credit.business.model.result.credit.CreditGetUserCreditScoreResult;
import com.guoanjia.common.credit.business.model.result.credit.CreditScoreBriefResult;
import com.guoanjia.common.credit.business.model.result.credit.CreditWatchlistBriefResult;
import com.guoanjia.common.credit.function.model.request.credit.AliCreditScoreBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliCreditWatchlistBriefRequest;
import com.guoanjia.common.credit.function.model.request.credit.AliGetUserCreditScoreRequest;

/**
 * 芝麻信用
 *
 * @author AsherLi
 * @version 1.0.00
 */
public interface AliCreditService {


    CreditScoreBriefResult creditScoreBrief(AliCreditScoreBriefRequest request);

    CreditWatchlistBriefResult creditWatchlistBrief(AliCreditWatchlistBriefRequest request);

    /**
     * 查询芝麻用户的芝麻信用评分
     * @param request
     * @return
     */
    CreditGetUserCreditScoreResult getUserCreditScore(AliGetUserCreditScoreRequest request);
}
