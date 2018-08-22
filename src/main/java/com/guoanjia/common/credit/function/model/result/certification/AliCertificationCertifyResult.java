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

package com.guoanjia.common.credit.function.model.result.certification;

import com.alipay.api.response.ZhimaCustomerCertificationCertifyResponse;
import com.guoanjia.common.credit.function.model.result.Result;
import com.guoanjia.common.credit.utils.GlobalConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 芝麻认证开始认证返回结果
 *
 * @author AsherLi
 * @version 1.0.00
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AliCertificationCertifyResult implements Result {

    /**
     * 执行状态
     */
    private GlobalConstant.ExecutionStatus executionStatus;
    /**
     * 返回结果
     */
    private ZhimaCustomerCertificationCertifyResponse response;

    public AliCertificationCertifyResult(ZhimaCustomerCertificationCertifyResponse response) {
        this.response = response;
    }

    /**
     * 判断状态是否成功
     *
     * @return 是否成功
     */
    @Override
    public boolean isExecutionSuccess() {
        return Objects.nonNull(response) && StringUtils.isNotBlank(response.getBody());
    }
}
