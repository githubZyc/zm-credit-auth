package com.guoanjia.common.credit.function.model.result.credit;

import com.alipay.api.response.ZhimaCreditScoreGetResponse;
import com.guoanjia.common.credit.function.model.result.Result;
import com.guoanjia.common.credit.utils.GlobalConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;

import static com.guoanjia.common.credit.utils.GlobalConstant.ExecutionStatus.SUCCESS;

/**
 * @Author: YanChuangZheng
 * @Description: 查询芝麻用户的芝麻信用评分返回结果
 * @Date: 15:02 2018/08/22
 **/
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AliGetUserCreditScoreResult implements Result {

    /**
     * 执行状态
     */
    private GlobalConstant.ExecutionStatus executionStatus;
    /**
     * 返回结果
     */
    private ZhimaCreditScoreGetResponse response;

    public AliGetUserCreditScoreResult(ZhimaCreditScoreGetResponse response) {
        this.response = response;
    }

    @Override
    public boolean isExecutionSuccess() {
        return Objects.nonNull(response) && SUCCESS.equals(executionStatus);
    }
}
