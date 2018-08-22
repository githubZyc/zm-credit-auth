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


import com.guoanjia.common.credit.function.model.request.certification.AliCertificationCertifyRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationInitializeRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationQueryRequest;
import com.guoanjia.common.credit.function.model.result.certification.AliCertificationCertifyResult;
import com.guoanjia.common.credit.function.model.result.certification.AliCertificationInitializeResult;
import com.guoanjia.common.credit.function.model.result.certification.AliCertificationQueryResult;

/**
 * 芝麻认证功能模块
 *
 * @author AsherLi
 * @version 1.0.00
 */
public interface AliCertificationFunction {
    /**
     * 阿里芝麻认证
     *
     * @param request 请求参数
     * @return 认证结果
     */
    AliCertificationCertifyResult certificationCertify(AliCertificationCertifyRequest request);

    /**
     * 阿里芝麻认证初始化
     *
     * @param request 请求参数
     * @return 认证唯一标识
     */
    AliCertificationInitializeResult certificationInitialize(AliCertificationInitializeRequest request);


    /**
     * 阿里芝麻认证查询
     *
     * @param request 请求参数
     * @return 认证结果
     */
    AliCertificationQueryResult certificationQuery(AliCertificationQueryRequest request);
}
