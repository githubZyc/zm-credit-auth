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

package com.guoanjia.common.credit.function.config;

import com.guoanjia.common.credit.function.service.AliCertificationFunction;
import com.guoanjia.common.credit.function.service.AliCreditFunction;
import com.guoanjia.common.credit.function.service.AliDataFunction;
import com.guoanjia.common.credit.function.service.impl.AliCertificationFunctionImpl;
import com.guoanjia.common.credit.function.service.impl.AliCreditFunctionImpl;
import com.guoanjia.common.credit.function.service.impl.AliDataFunctionImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能模块BEAN初始化
 *
 * @author AsherLi
 * @version 1.0.00
 */
@Configuration
@ConditionalOnClass(AliCreditFunction.class)
@EnableConfigurationProperties(AliProperties.class)
public class AliConfiguration {

    /**
     * 配置参数
     */
    private final AliProperties properties;

    public AliConfiguration(final AliProperties properties) {
        this.properties = properties;
    }

    /**
     * 配置类初始化
     *
     * @return 配置类
     */
    @Bean
    @ConditionalOnMissingBean
    public AliConfig aliConfig() {
        return new AliConfig().setProperties(properties);
    }

    /**
     * 阿里信用模块初始化
     *
     * @param aliConfig 配置类
     * @return 信用模块
     */
    @Bean
    @ConditionalOnMissingBean
    public AliCreditFunction aliCreditFunction(AliConfig aliConfig) {
        return new AliCreditFunctionImpl().initAliClient(aliConfig);
    }

    /**
     * 阿里数据模块初始化
     *
     * @param aliConfig 配置类
     * @return 数据模块
     */
    @Bean
    @ConditionalOnMissingBean
    public AliDataFunction aliDataFunction(AliConfig aliConfig) {
        return new AliDataFunctionImpl().initAliClient(aliConfig);
    }

    /**
     * 阿里信用模块初始化
     *
     * @param aliConfig 配置类
     * @return 信用模块
     */
    @Bean
    @ConditionalOnMissingBean
    public AliCertificationFunction aliCertificationFunction(AliConfig aliConfig) {
        return new AliCertificationFunctionImpl().initAliClient(aliConfig);
    }

}
