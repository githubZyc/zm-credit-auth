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

package com.guoanjia.common.credit.business.model.result.certification;

import com.google.gson.annotations.SerializedName;
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
 * 芝麻认证查询返回模型
 *
 * @author AsherLi
 * @version 1.0.00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "芝麻认证查询返回数据模型")
public class CertificationQueryResult extends BaseResult implements Serializable {

    private static final long serialVersionUID = -3019817071810292176L;

    /**
     * 认证是否通过,通过为true，不通过为false
     */
    @ApiModelProperty(value = "认证是否通过,通过为true，不通过为false", allowableValues = "true,false")
    private String passed;

    /**
     * 认证不通过的原因
     */
    @ApiModelProperty(value = "认证不通过的原因")
    @SerializedName("failed_reason")
    private String failedReason;

    /**
     * 认证的主体信息，一般的认证场景返回为空
     */
    @ApiModelProperty(value = "认证的主体信息，一般的认证场景返回为空")
    @SerializedName("identity_info")
    private String identityInfo;

    /**
     * 认证的主体属性信息，一般的认证场景都是返回空
     */
    @ApiModelProperty(value = "认证的主体属性信息，一般的认证场景都是返回空")
    @SerializedName("attribute_info")
    private String attributeInfo;

    /**
     * 包含了认证过程中的认证材料和过程记录
     */
    @ApiModelProperty(value = "包含了认证过程中的认证材料和过程记录")
    @SerializedName("channel_statuses")
    private String channelStatuses;


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
