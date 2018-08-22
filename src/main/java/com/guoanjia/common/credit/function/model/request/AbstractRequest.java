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

package com.guoanjia.common.credit.function.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author AsherLi
 * @version 1.0.00
 **/
@Data
public abstract class AbstractRequest {

    /**
     * 用户授权TOKEN
     */
    @ApiModelProperty(value = "用户授权TOKEN",allowEmptyValue = true)
    private String appAuthToken;

    /**
     * 属性校验
     */
    public abstract void checkConstraints();

    /**
     * 获取Json格式数据
     *
     * @return Json数据
     */
    @ApiModelProperty(hidden = true)
    public abstract String getBizContent();






}
