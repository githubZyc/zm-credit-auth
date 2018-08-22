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

package com.guoanjia.common.credit.function.model.request.certification;

import com.google.gson.annotations.SerializedName;
import com.guoanjia.common.credit.function.model.request.AbstractRequest;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 芝麻认证开始认证
 *
 * @author AsherLi
 * @version 1.0.00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description ="芝麻认证开始认证请求数据模型" )
public class AliCertificationCertifyRequest extends AbstractRequest implements Serializable {

    private static final long serialVersionUID = -7846116487693397075L;

    /**
     * 同步回调通知地址
     */
    @ApiModelProperty(value = "同步回调通知地址")
    private String returnUrl;

    /**
     * 一次认证的唯一标识，在完成芝麻认证初始化后可以获取
     */
    @ApiModelProperty(value ="一次认证的唯一标识，在完成芝麻认证初始化后可以获取" )
    @NotBlank
    @SerializedName("biz_no")
    private String bizNo;

    @Override
    public void checkConstraints() {

    }

    @Override
    public String getBizContent() {
        return BaseGsonBuilder.create().toJson(this);
    }
}