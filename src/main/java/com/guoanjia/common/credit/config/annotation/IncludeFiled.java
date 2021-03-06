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

package com.guoanjia.common.credit.config.annotation;

import com.guoanjia.common.credit.config.annotation.validator.IncludeFiledValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author AsherLi
 * @version 1.0.00
 **/
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IncludeFiledValidator.class)
public @interface IncludeFiled {

    String[] include() default {};

    String message() default "";

    String des() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
