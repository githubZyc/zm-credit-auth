package com.guoanjia.common.credit.business.model.result.credit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * @Author: YanChuangZheng
 * @Description: 查询芝麻用户的芝麻信用评分返回结果
 * @Date: 15:02 2018/08/22
 **/
@EqualsAndHashCode()
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "芝麻用户的芝麻信用评分返回结果")
public class CreditGetUserCreditScoreResult {
    private static final long serialVersionUID = 3493575882027876927L;

    /**
     * 唯一标示每一次接口调用
     */
    @ApiModelProperty(value = "芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账")
    private String bizNo;

    /**
     * 唯一标示每一次接口调用
     */
    @ApiModelProperty(value = "用户的芝麻分。分值范围[350,950]。如果用户数据不足，无法评分时，返回字符串N/A。")
    private String zmScore;



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
