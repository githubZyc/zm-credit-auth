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

package com.guoanjia.common.credit.business.model.result.credit;

import com.guoanjia.common.credit.business.model.result.BaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 行业关注名单普惠版返回数据模型
 *
 * @author AsherLi
 * @version 1.0.00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "行业关注名单普惠版返回数据模型", parent = BaseResult.class)
public class CreditWatchlistBriefResult extends BaseResult implements Serializable {

    private static final long serialVersionUID = 3493575882027876927L;

    /**
     * 唯一标示每一次接口调用
     */
    @ApiModelProperty(value = "唯一标示")
    private String bizNo;

    /**
     * 用户风险等级
     * 0 未命中逾期名单
     * 1 命中一类名单，例如用户有一周以内的轻微逾期
     * 2 命中二类名单，例如用户有一周以上中等逾期
     * 3 命中三类名单，例如用户有一个月以上的严重逾期
     * N/A 无法评估该用户逾期状况，例如未获得用户授权。
     */
    @ApiModelProperty(value = "用户风险等级" +
            "\n\t0 未命中逾期名单" +
            "\n\t1 命中一类名单，例如用户有一周以内的轻微逾期" +
            "\n\t2 命中二类名单，例如用户有一周以上中等逾期" +
            "\n\t3 命中三类名单，例如用户有一个月以上的严重逾期" +
            "\n\tN/A 无法评估该用户逾期状况，例如未获得用户授权", allowableValues = "0,1,2,3,N/A")
    private String level;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
