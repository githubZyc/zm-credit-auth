package com.guoanjia.common.credit.config.model;

import lombok.Data;

@Data
public class GetAccessToken {

    public String accessToken;

    public String userId;

    public String refresh_token;
}
