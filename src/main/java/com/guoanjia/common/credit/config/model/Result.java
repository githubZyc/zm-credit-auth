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

package com.guoanjia.common.credit.config.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author AsherLi
 * @version 1.0.00
 */
@Getter
@ApiModel(description = "返回结果")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -153261482683519145L;

    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码",example = "200",position = 0)
    protected int code;

    /**
     * 错误原因
     */
    @ApiModelProperty(value = "错误原因",example = "请求执行成功",position = 1)
    protected String msg;

    /**
     * 返回数据
     */
    @SuppressWarnings("NonSerializableFieldInSerializableClass")
    @ApiModelProperty(value = "返回数据",position = 2)
    protected T data;

    public Result() {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public Result(T data) {
        this.data = data;
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public Result(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

}
