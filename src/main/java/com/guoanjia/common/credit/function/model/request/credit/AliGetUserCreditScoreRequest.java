package com.guoanjia.common.credit.function.model.request.credit;

import com.google.gson.annotations.SerializedName;
import com.guoanjia.common.credit.function.model.request.AbstractRequest;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: YanChuangZheng
 * @Description: 查询芝麻用户的芝麻信用评分
 * @Date: 15:02 2018/08/22
 **/

@EqualsAndHashCode()
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查询芝麻用户的芝麻信用评分", parent = AliGetUserCreditScoreRequest.class)
public class AliGetUserCreditScoreRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -1645702965461288476L;

    /**
     * 商户请求的唯一标志，64位长度的字母数字下划线组合。
     * 该标识作为对账的关键信息，商户要保证其唯一性，对于用户使用相同transaction_id的查询，芝麻在一天（86400秒）内返回首次查询数据，超过有效期的查询即为无效并返回异常，有效期内的重复查询不重新计费
     */
    @ApiModelProperty(value = "商户请求的唯一标志，64位长度的字母数字下划线组合" +
            "\n\t该标识作为对账的关键信息，商户要保证其唯一性" +
            "\n\t对于用户使用相同transaction_id的查询，芝麻在一天（86400秒）内返回首次查询数据，超过有效期的查询即为无效并返回异常，有效期内的重复查询不重新计费",
            required = true)
    @NotBlank
    @SerializedName(value = "transaction_id")
    private String transactionId;

    /**
     * 产品码
     */
    @ApiModelProperty(value = "产品码", readOnly = true, hidden = true)
    @SerializedName("product_code")
    private String productCode;

    @Override
    public void checkConstraints() {

    }

    @Override
    public String getBizContent() {
        return BaseGsonBuilder.create().toJson(this);
    }
}
