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

package com.guoanjia.common.credit.function.model.request.credit;

import com.google.gson.annotations.SerializedName;
import com.guoanjia.common.credit.config.annotation.IncludeFiled;
import com.guoanjia.common.credit.exception.ParameterException;
import com.guoanjia.common.credit.function.model.request.AbstractRequest;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import com.guoanjia.common.credit.utils.RegexRule;
import com.guoanjia.common.credit.utils.RegexUtil;
import com.guoanjia.common.credit.utils.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

import static com.guoanjia.common.credit.utils.GlobalConstant.AliProductCode.CREDIT_SCORE;
import static com.guoanjia.common.credit.utils.GlobalConstant.ParameterConstants.CertType.*;
import static com.guoanjia.common.credit.utils.GlobalConstant.ParameterConstants.MAX_SCORE;
import static com.guoanjia.common.credit.utils.GlobalConstant.ParameterConstants.MIN_SCORE;

/**
 * 芝麻信用评分普惠版请求数据模型
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
@ApiModel(description = "芝麻信用评分普惠版请求数据模型", parent = AbstractRequest.class)
public class AliCreditScoreBriefRequest extends AbstractRequest implements Serializable {

    private static final long serialVersionUID = 970980818608684311L;

    /**
     * 商户请求的唯一标志，64位长度的字母数字下划线组合。
     * 该标识作为对账的关键信息，商户要保证其唯一性，对于用户使用相同transaction_id的查询，芝麻在一天（86400秒）内返回首次查询数据，超过有效期的查询即为无效并返回异常，有效期内的重复查询不重新计费
     */
    @ApiModelProperty(value = "商户请求的唯一标志，64位长度的字母数字下划线组合" +
            "\n\t该标识作为对账的关键信息，商户要保证其唯一性。" +
            "\n\t对于用户使用相同transaction_id的查询，芝麻在一天（86400秒）内返回首次查询数据，超过有效期的查询即为无效并返回异常，有效期内的重复查询不重新计费",
            required = true)
    @NotBlank
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * 产品码
     */
    @ApiModelProperty(value = "产品码", readOnly = true, hidden = true)
    @SerializedName("product_code")
    private String productCode;

    /**
     * 证件类型 目前支持三种IDENTITY_CARD(身份证),PASSPORT(护照),ALIPAY_USER_ID(支付宝uid)
     */
    @ApiModelProperty(value = "证件类型", required = true, allowableValues = "IDENTITY_CARD,PASSPORT,ALIPAY_USER_ID")
    @IncludeFiled(include = {IDENTITY_CARD, PASSPORT, ALIPAY_USER_ID})
    @SerializedName("cert_type")
    private String certType;

    /**
     * 对应的证件号(未脱敏)或支付宝uid
     */
    @ApiModelProperty(value = "对应的证件号(未脱敏)或支付宝uid", required = true)
    @NotBlank
    @SerializedName("cert_no")
    private String certNo;

    /**
     * 用户姓名 当证件类型为ALIPAY_USER_ID时不需要传入
     */
    @ApiModelProperty(value = "用户姓名" +
            "\n\t当证件类型为ALIPAY_USER_ID时不需要传入", allowEmptyValue = true)
    private String name;

    /**
     * 350～950之间 业务判断的准入标准 建议业务确定一个稳定的判断标准 频繁的变更该标准可能导致接口被停用
     */
    @ApiModelProperty(value = "业务判断的准入标准" +
            "\n\t建议业务确定一个稳定的判断标准 频繁的变更该标准可能导致接口被停用", required = true, allowableValues = "range[350,950]")
    @Min(value = MIN_SCORE, message = "信用评分最低350")
    @Max(value = MAX_SCORE, message = "信用评分最高950")
    @SerializedName("admittance_score")
    private int admittanceScore;

    @Override
    public void checkConstraints() {
        // 唯一标识符生成
        this.transactionId = Utils.createTransactionId(this.transactionId);

        // 强制覆盖产品码
        this.productCode = CREDIT_SCORE;

        if (Objects.equals(IDENTITY_CARD, this.certType) && !RegexUtil.isMatchString(this.certNo, RegexRule.ID_CARD, false)) {
            throw new ParameterException("[参数 certType 身份证号码格式不正确]");
        }
        if (Objects.equals(PASSPORT, this.certType) && !RegexUtil.isMatchString(this.certNo, RegexRule.PASSPORT, false)) {
            throw new ParameterException("[参数 certType 护照号码格式不正确]");
        }
        if (!Objects.equals(ALIPAY_USER_ID, this.certType) && StringUtils.isBlank(this.name)) {
            throw new ParameterException("[参数 name 非支付宝UID类型姓名不能为空]");
        }
    }

    @Override
    public String getBizContent() {
        return BaseGsonBuilder.create().toJson(this);
    }


}