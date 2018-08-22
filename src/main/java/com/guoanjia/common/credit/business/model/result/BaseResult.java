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

package com.guoanjia.common.credit.business.model.result;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class BaseResult implements Serializable {

    private static final long serialVersionUID = -2745129205264509906L;

    /**
     * 返回码
     */
    @ApiModelProperty(value = "返回码")
    private String code;

    /**
     * 返回码描述
     */
    @ApiModelProperty(value = "返回码描述")
    private String msg;

    /**
     * 业务返回码
     */
    @ApiModelProperty(value = "业务返回码")
    @SerializedName("sub_code")
    private String subCode;

    /**
     * 业务返回码描述
     */
    @ApiModelProperty(value = "业务返回码描述")
    @SerializedName("sub_msg")
    private String subMsg;

    /**
     * 原始返回数据
     */
    @ApiModelProperty(value = "原始返回数据")
    private String body;

    /**
     * 原始请求数据
     */
    @ApiModelProperty(value = "原始请求数据")
    private Map<String, String> params;



}
