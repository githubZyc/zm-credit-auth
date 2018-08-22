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

import com.guoanjia.common.credit.exception.ExceptionInfo;
import lombok.Getter;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author AsherLi
 * @version 1.0.00
 */
@Getter
public class DebugResult<T> extends Result implements Serializable {


    private static final long serialVersionUID = 5269974946974695595L;
    private T data;

    private ExceptionInfo exception;


    public DebugResult(ResultEnum resultEnum,ExceptionInfo exception) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.exception = exception;
    }

    public DebugResult(T data,ExceptionInfo exception) {
        this.data = data;
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.exception = exception;
    }

    public DebugResult(ResultEnum resultEnum, T data,ExceptionInfo exception) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
        this.exception = exception;
    }

}
