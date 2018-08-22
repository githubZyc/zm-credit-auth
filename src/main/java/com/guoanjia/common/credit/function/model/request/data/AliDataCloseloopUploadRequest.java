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
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;

import static com.guoanjia.common.credit.utils.GlobalConstant.ParameterConstants.SCENE_CODE;

/**
 * @author AsherLi
 * @version 1.0.00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "批量数据传入请求数据模型")
public class AliDataCloseloopUploadRequest extends AbstractRequest implements Serializable {

    private static final long serialVersionUID = -212908070271552137L;


    /**
     * 文件的编码，如果文件格式是UTF-8，则填写UTF-8，如果文件格式是GBK，则填写GBK。
     */
    @ApiModelProperty(value = "文件的编码，如果文件格式是UTF-8，则填写UTF-8，如果文件格式是GBK，则填写GBK",required = true)
    @NotBlank
    private String fileCharset;

    /**
     * 文件数据记录条数，如file字段中的record数组有10条数据，那么就填10。
     */
    @ApiModelProperty(value = "文件数据记录条数，如file字段中的record数组有10条数据，那么就填10",required = true)
    @NotBlank
    private String records;

    /**
     * 传入的json格式的文件，其中records属性必填。json中的字段可以通过如下步骤获取：
     * 首先调用zhima.merchant.data.upload.initialize接口获取数据模板，该接口会返回一个数据模板文件的url地址，如：
     * http://zmxymerchant-prod.oss-cn-shenzhen.zmxy.com.cn/openApi/openDoc/信用护航-负面记录和信用足迹商户数据模板V1.0.xlsx，
     * 该数据模板文件详细列出了需要传入的字段，及各字段的要求，data中的各字段就是该文件中列出的字段编码。
     */
    @ApiModelProperty(value = "传入的json格式的文件" +
            "\n\t其中records属性必填。json中的字段可以通过如下步骤获取：" +
            "\n\t首先调用zhima.merchant.data.upload.initialize接口获取数据模板，该接口会返回一个数据模板文件的url地址，如：" +
            "\n\thttp://zmxymerchant-prod.oss-cn-shenzhen.zmxy.com.cn/openApi/openDoc/信用护航-负面记录和信用足迹商户数据模板V1.0.xlsx，" +
            "\n\t该数据模板文件详细列出了需要传入的字段，及各字段的要求，data中的各字段就是该文件中列出的字段编码",required = true,hidden = true,readOnly = true)
    @NotNull
    private File file;

    /**
     * 单条数据的数据列，多个列以逗号隔开。
     */
    @ApiModelProperty(value = "单条数据的数据列，多个列以逗号隔开",required = true)
    @NotBlank
    private String columns;

    /**
     * 公用回传参数（非必填），该参数会透传给商户，商户可以用于业务逻辑处理，请使用json格式。
     */
    @ApiModelProperty(value ="公用回传参数（非必填），这个字段由商户传入，系统透传给商户，便于商户做逻辑关联，请使用json格式" )
    @SerializedName(value = "biz_ext_params")
    private String bizExtParams;

    /**
     * 主键列使用传入字段进行组合，也可以使用传入的某个单字段（确保主键稳定，而且可以很好的区分不同的数据）。
     * 例如order_no,pay_month或者order_no,bill_month组合，对于一个order_no只会有一条数据的情况，直接使用order_no作为主键列。
     */
    @ApiModelProperty(value = "主键列使用传入字段" +
            "\n\t主键列使用传入字段进行组合，也可以使用传入的某个单字段（确保主键稳定，而且可以很好的区分不同的数据）" +
            "\n\t 例如order_no,pay_month或者order_no,bill_month组合，对于一个order_no只会有一条数据的情况，直接使用order_no作为主键列",required = true)
    @NotBlank
    @SerializedName(value = "primary_key_columns")
    private String primaryKeyColumns;

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


    @Override
    public void checkConstraints() {

    }

    @Override
    public String getBizContent() {
        return BaseGsonBuilder.create().toJson(this);
    }


}