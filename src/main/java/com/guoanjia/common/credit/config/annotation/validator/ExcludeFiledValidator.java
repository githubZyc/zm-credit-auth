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

package com.guoanjia.common.credit.config.annotation.validator;

import com.guoanjia.common.credit.config.annotation.ExcludeFiled;
import com.guoanjia.common.credit.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @author AsherLi
 * @version 1.0.00
 **/
public class ExcludeFiledValidator implements ConstraintValidator<ExcludeFiled, String> {

    private String[] exclude;
    private String message;
    private String description;

    private static final String DEFAULT_EMPTY_MESSAGE = "{}不能为空或null";
    private static final String DEFAULT_EXCLUDE_MESSAGE = "{}不能在 {} 中选择";


    @Override
    public void initialize(ExcludeFiled annotation) {
        this.exclude = annotation.exclude();
        this.message = annotation.message();
        this.description = annotation.description();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(StringUtils.createMessage(this.message, DEFAULT_EMPTY_MESSAGE, this.description)).addConstraintViolation();
            return false;
        }

        if (Arrays.asList(this.exclude).contains(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(StringUtils.createMessage(this.message, DEFAULT_EXCLUDE_MESSAGE, this.description, Arrays.toString(this.exclude))).addConstraintViolation();
            return false;
        }
        return true;
    }
}
