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

package com.guoanjia.common.credit.function.model.request.data;

import com.google.gson.annotations.SerializedName;
import com.guoanjia.common.credit.config.annotation.IncludeFiled;
import com.guoanjia.common.credit.exception.ParameterException;
import com.guoanjia.common.credit.function.model.request.AbstractRequest;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Arrays;

import static com.guoanjia.common.credit.utils.GlobalConstant.ParameterConstants.SCENE_CODE;

/**
 * 数据上传
 *
 * @author AsherLi
 * @version 1.0.00
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "数据上传初始化请求数据模型")
public class AliDataUploadInitializeRequest extends AbstractRequest implements Serializable {

    private static final long serialVersionUID = 7556817408124110838L;

    /**
     * 数据应用的场景编码，场景码和场景名称（数字或字符串为场景码）如下：
     * 8：数据反馈
     * 32：骑行
     * CAR_RENTING：租车行业解决方案
     */
    @ApiModelProperty(value = "数据应用的场景编码" +
            "\n\t8：数据反馈" +
            "\n\t32：骑行" +
            "\n\tCAR_RENTING：租车行业解决方案", required = true, allowableValues = "8,32,CAR_RENTING")
    @NotBlank
    @SerializedName(value = "scene_code")
    @IncludeFiled(include = {"8", "32", "CAR_RENTING"}, des = "数据应用的场景编码")
    private String sceneCode;

    @Override
    public void checkConstraints() {

    }

    @Override
    public String getBizContent() {
        return BaseGsonBuilder.create().toJson(this);
    }
}
