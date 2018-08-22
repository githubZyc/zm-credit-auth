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
import com.guoanjia.common.credit.function.model.request.AbstractRequest;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;

import static com.guoanjia.common.credit.utils.GlobalConstant.ParameterConstants.SCENE_CODE;

/**
 * 单条数据上传
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
@ApiModel(description = "单条数据上传请求数据模型")
public class AliDataSingleUploadRequest extends AbstractRequest implements Serializable {

    private static final long serialVersionUID = 3957338391631373322L;

    /**
     * 数据应用的场景编码，场景码和场景名称（数字或字符串为场景码）如下：
     * 8：数据反馈
     * 32：骑行
     * CAR_RENTING：租车行业解决方案
     * 每个场景码对应的数据模板不一样，请使用zhima.merchant.data.upload.initialize接口获取场景码对应的数据模板。
     */
    @ApiModelProperty(value = "数据应用的场景编码" +
            "\n\t8：数据反馈" +
            "\n\t32：骑行" +
            "\n\tCAR_RENTING：租车行业解决方案", required = true, allowableValues = "8,32,CAR_RENTING")
    @NotBlank
    @SerializedName(value = "scene_code")
    @IncludeFiled(include = {"8", "32", "CAR_RENTING"}, des = "数据应用的场景编码")
    private String sceneCode;

    /**
     * 传入的json数据，商户通过json格式将数据传给芝麻 ， json中的字段可以通过如下步骤获取：
     * 首先调用zhima.merchant.data.upload.initialize接口获取数据模板，该接口会返回一个数据模板文件的url地址，如：
     * http://zmxymerchant-prod.oss-cn-shenzhen.zmxy.com.cn/openApi/openDoc/信用护航-负面记录和信用足迹商户数据模板V1.0.xlsx，
     * 该数据模板文件详细列出了需要传入的字段，及各字段的要求，data中的各字段就是该文件中列出的字段编码。
     */
    @ApiModelProperty(value = "传入的json数据" +
            "\n\t商户通过json格式将数据传给芝麻 ， json中的字段可以通过如下步骤获取：" +
            "\n\t首先调用zhima.merchant.data.upload.initialize接口获取数据模板，该接口会返回一个数据模板文件的url地址，如：" +
            "\n\thttp://zmxymerchant-prod.oss-cn-shenzhen.zmxy.com.cn/openApi/openDoc/信用护航-负面记录和信用足迹商户数据模板V1.0.xlsx" +
            "\n\t该数据模板文件详细列出了需要传入的字段，及各字段的要求，data中的各字段就是该文件中列出的字段编码",required = true )
    @NotBlank
    private String data;

    /**
     * 主键列使用传入字段进行组合，也可以使用传入的某个单字段（确保主键稳定，而且可以很好的区分不同的数据）。
     * 例如order_no,pay_month或者order_no,bill_month组合，对于一个order_no只会有一条数据的情况，直接使用order_no作为主键列 。
     */
    @ApiModelProperty(value = "主键列使用传入字段" +
            "\n\t主键列使用传入字段进行组合，也可以使用传入的某个单字段（确保主键稳定，而且可以很好的区分不同的数据）" +
            "\n\t 例如order_no,pay_month或者order_no,bill_month组合，对于一个order_no只会有一条数据的情况，直接使用order_no作为主键列",required = true)
    @NotBlank
    @SerializedName(value = "primary_keys")
    private String primaryKeys;

    /**
     * 公用回传参数（非必填），这个字段由商户传入，系统透传给商户，便于商户做逻辑关联，请使用json格式。
     */
    @ApiModelProperty(value ="公用回传参数（非必填），这个字段由商户传入，系统透传给商户，便于商户做逻辑关联，请使用json格式" )
    @SerializedName(value = "biz_ext_params")
    private String bizExtParams;

    @Override
    public void checkConstraints() {

    }

    @Override
    public String getBizContent() {
        return BaseGsonBuilder.create().toJson(this);
    }


}