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

package com.guoanjia.common.credit.business.model.result.credit;

import com.guoanjia.common.credit.business.model.result.BaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 芝麻信用评分普惠版返回数据模型
 *
 * @author AsherLi
 * @version 1.0.00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "芝麻信用评分普惠版返回数据模型")
public class CreditScoreBriefResult extends BaseResult implements Serializable {

    private static final long serialVersionUID = 3493575882027876927L;

    /**
     * 唯一标示每一次接口调用
     */
    @ApiModelProperty(value = "唯一标示")
    private String bizNo;

    /**
     * 准入判断结果
     * Y(用户的芝麻分大于等于准入分数)
     * N（用户的芝麻分小于准入分数）
     * N/A（无法评估，例如用户未开通芝麻信用,或芝麻采集的信息不足以评估该用户的信用）
     */
    @ApiModelProperty(value = "准入判断结果" +
            "\n\tY(用户的芝麻分大于等于准入分数)" +
            "\n\tN（用户的芝麻分小于准入分数）" +
            "\n\tN/A（无法评估，例如用户未开通芝麻信用,或芝麻采集的信息不足以评估该用户的信用）", allowableValues = "Y,N,N/A")
    private String isAdmittance;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
