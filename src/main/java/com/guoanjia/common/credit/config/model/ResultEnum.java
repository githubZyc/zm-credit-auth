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

/**
 * 返回结果枚举值
 *
 * @author AsherLi
 * @version 1.0.00
 */
public enum ResultEnum {

    /**
     * 异常
     */
    EXCEPTION(400 - 600, "HandlerException 占用400-500序号"),


    PARAMETER_VALIDATE_FAILED(400, "请求参数有误"),
    SUCCESS(200, "请求执行成功"),
    UN_KNOW_ERROR(-1, "未知错误");


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public ResultEnum setCode(final Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultEnum setMsg(final String msg) {
        this.msg = msg;
        return this;
    }
}
