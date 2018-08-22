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


import com.guoanjia.common.credit.business.model.result.certification.CertificationCertifyResult;
import com.guoanjia.common.credit.business.model.result.certification.CertificationInitializeResult;
import com.guoanjia.common.credit.business.model.result.certification.CertificationQueryResult;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationCertifyRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationInitializeRequest;
import com.guoanjia.common.credit.function.model.request.certification.AliCertificationQueryRequest;

/**
 * 芝麻认证业务层
 *
 * @author AsherLi
 * @version 1.0.00
 */
public interface AliCertificationService {

    /**
     * 芝麻认证初始化接口
     *
     * @param request 初始化参数
     * @return 初始化结果
     */
    CertificationInitializeResult certificationInitialize(AliCertificationInitializeRequest request);

    /**
     * 芝麻认证开始
     *
     * @param request 参数
     * @return 认证结果
     */
    CertificationCertifyResult certificationCertify(AliCertificationCertifyRequest request);

    /**
     * 芝麻认证结果查询
     *
     * @param request 参数
     * @return 认证结果
     */
    CertificationQueryResult certificationQuery(AliCertificationQueryRequest request);
}
