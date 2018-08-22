package com.guoanjia.common.credit.config.model;

import lombok.Data;

/**
 * Created by gaozhou on 2018/06/25/.
 */
@Data
public class ZhimaCreditScoreQueryModel {

    private String id;  //系统UserId（个人中心）
    private String aliUserID; //阿里返回UserId
    private String accessToken; //accessToken
    private String refreshToken; //刷新Token
}
