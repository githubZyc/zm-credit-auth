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
import com.guoanjia.common.credit.config.annotation.IncludeFiled;
import com.guoanjia.common.credit.exception.ParameterException;
import com.guoanjia.common.credit.function.model.request.AbstractRequest;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import com.guoanjia.common.credit.utils.RegexRule;
import com.guoanjia.common.credit.utils.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import static com.guoanjia.common.credit.utils.GlobalConstant.AliProductCode.CERTIFICATION_INITIALIZE;
import static com.guoanjia.common.credit.utils.GlobalConstant.ParameterConstants.BizCode.*;
import static com.guoanjia.common.credit.utils.GlobalConstant.ParameterConstants.CertType.IDENTITY_CARD;
import static com.guoanjia.common.credit.utils.GlobalConstant.ParameterConstants.IDENTITY_TYPE;


/**
 * 芝麻认证初始化
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
@ApiModel(description = "芝麻认证初始化请求数据模型")
public class AliCertificationInitializeRequest extends AbstractRequest implements Serializable {

    private static final long serialVersionUID = -73119276162974516L;

    /**
     * 商户请求的唯一标志，商户要保证其唯一性。值为32位长度的字母数字下划线组合。
     * 建议：前面几位字符是商户自定义的简称，中间可以使用一段日期，结尾可以使用一个序列
     */
    @ApiModelProperty(value = "商户请求的唯一标志，商户要保证其唯一性。值为32位长度的字母数字下划线组合" +
            "\n\t建议：前面几位字符是商户自定义的简称，中间可以使用一段日期，结尾可以使用一个序列", required = true, example = "GAJ73119276162974516L")
    @NotBlank
    @SerializedName(value = "transaction_id")
    private String transactionId;

    /**
     * 产品码
     */
    @ApiModelProperty(value = "产品码", readOnly = true, hidden = true)
    @SerializedName("product_code")
    private String productCode;

    /**
     * 认证场景码，常用的场景码有：FACE：人脸认证。入参支持的认证场景码和商户签约的认证场景相关，详见芝麻认证快速接入文档
     */
    @ApiModelProperty(value = "认证场景码" +
            "\n\tFACE:人脸认证" +
            "\n\tCERT_PHOTO_FACE:多因子证照和人脸认证" +
            "\n\tCERT_PHOTO:多因子证照认证" +
            "\n\tSMART_FACE:多因子快捷认证" +
            "\n\tFACE_SDK:人脸认证SDK", required = true, allowableValues = "FACE,CERT_PHOTO_FACE,CERT_PHOTO,SMART_FACE,FACE_SDK")
    @IncludeFiled(include = {FACE, FACE_SDK, SMART_FACE, CERT_PHOTO, CERT_PHOTO_FACE},des = "认证场景码")
    @SerializedName("biz_code")
    private String bizCode;

    /**
     * identity_type":”CERT_INFO”，是指入参类型为证件信息。芝麻认证有很多认证场景（biz_code），不同认证认证场景（biz_code）支持的入参类型不同，详细请见芝麻认证快速接入文档里面的表格
     */
    @ApiModelProperty(value = "身份信息")
    @SerializedName("identity_param")
    private IdentityParam identityParam;

    /**
     * 商户可选的一些设置
     */
    @ApiModelProperty(value = "商户可选的一些设置")
    @SerializedName("merchant_config")
    private String merchantConfig;

    /**
     * 扩展业务参数，暂时没有用到，接口预留
     */
    @ApiModelProperty(value = "扩展业务参数，暂时没有用到，接口预留")
    @SerializedName("ext_biz_param")
    private int extBizParam;

    /**
     * 同步回调通知地址
     */
    @ApiModelProperty(value = "同步回调通知地址")
    private String returnUrl;

    @Override
    public void checkConstraints() {

        // 强制覆盖产品码
        this.productCode = CERTIFICATION_INITIALIZE;

        if (Arrays.asList(FACE, FACE_SDK, SMART_FACE).contains(this.bizCode)) {
            if (Objects.isNull(this.identityParam)) {
                throw new ParameterException("[参数 identityParam 身份认证信息不能为null]");
            }

            if (!Arrays.asList(IDENTITY_TYPE).contains(this.identityParam.identityType)) {
                throw new ParameterException("[参数 identityParam.identityType 身份认证类型只能在 {} 中选择]", Arrays.toString(IDENTITY_TYPE));
            }

            if (!Objects.equals(this.identityParam.certType, IDENTITY_CARD)) {
                throw new ParameterException("[参数 identityParam.certType 当前认证类型只支持身份证认证]");
            }

            if (Objects.equals(this.identityParam.certType, IDENTITY_CARD) && !RegexUtil.isMatchString(this.identityParam.certNo, RegexRule.ID_CARD, false)) {
                throw new ParameterException("[参数 identityParam.certType 身份证号码格式不正确]");
            }

            if (StringUtils.isBlank(this.identityParam.certName)) {
                throw new ParameterException("[参数 identityParam.certName 证件持有人姓名不能为空]");
            }

            if (StringUtils.isBlank(this.identityParam.certNo)) {
                throw new ParameterException("[参数 identityParam.certNo 证件号码不能为空]");
            }
        }
    }

    @Override
    public String getBizContent() {
        return BaseGsonBuilder.create().toJson(this);
    }

    @Data
    @Builder
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    private static class IdentityParam {

        /**
         * 身份类型
         */
        @ApiModelProperty(value = "身份类型", allowEmptyValue = true, allowableValues = "CERT_INFO")
        @SerializedName("identity_type")
        private String identityType;

        /**
         * 证件类型
         */
        @ApiModelProperty(value = "证件信息入参，现在只支持身份证", allowableValues = "IDENTITY_CARD")
        @SerializedName("cert_type")
        private String certType;

        /**
         * 证件姓名
         */
        @ApiModelProperty(value = "用户姓名")
        @SerializedName("cert_name")
        private String certName;

        /**
         * 证件号码
         */
        @ApiModelProperty(value = "证件号码")
        @SerializedName("cert_no")
        private String certNo;
    }
}