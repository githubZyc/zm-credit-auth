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

package com.guoanjia.common.credit.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author AsherLi
 * @version 1.0.00
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        // 指定允许MIME协议
        Set<String> produces = new HashSet<>();
        produces.add(MediaType.TEXT_XML_VALUE);
        produces.add(MediaType.TEXT_PLAIN_VALUE);
        produces.add(MediaType.TEXT_MARKDOWN_VALUE);
        produces.add(MediaType.APPLICATION_XML_VALUE);
        produces.add(MediaType.APPLICATION_JSON_VALUE);
        produces.add(MediaType.APPLICATION_JSON_UTF8_VALUE);
        produces.add(MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        // 自定义异常信息
        ArrayList<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message("请求执行成功").build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message("请求参数错误").build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message("权限认证失败").build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message("请求资源不可用").build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("请求资源不存在").build());
        responseMessages.add(new ResponseMessageBuilder().code(409).message("请求资源冲突").build());
        responseMessages.add(new ResponseMessageBuilder().code(415).message("请求格式错误").build());
        responseMessages.add(new ResponseMessageBuilder().code(423).message("请求资源被锁定").build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
        responseMessages.add(new ResponseMessageBuilder().code(501).message("请求方法不存在").build());
        responseMessages.add(new ResponseMessageBuilder().code(503).message("服务暂时不可用").build());
        responseMessages.add(new ResponseMessageBuilder().code(-1).message("未知异常").build());

        Set<String> protocols = new HashSet<>();
        protocols.add("http");
        protocols.add("https");

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.guoanjia.common"))
                .paths(PathSelectors.any())
                .build()
                .groupName("common-credit")
                .pathMapping("/")
                .produces(produces)
                .consumes(produces)
                .protocols(protocols)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                .host("127.0.0.1:28083")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("AsherLi", "https://www.wizhishu.com", "lzz0103@163.com");
        return new ApiInfoBuilder()
                .title("国安家信用积分模块API文档")
                .description("国安家通用组件分支信用模块API接口文档")
                //服务条款网址
                .termsOfServiceUrl("https://www.wizhishu.com")
                .version("1.0.00")
                .contact(contact)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
