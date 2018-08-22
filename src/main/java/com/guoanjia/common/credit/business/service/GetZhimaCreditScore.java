package com.guoanjia.common.credit.business.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.ZhimaAuthInfoAuthqueryRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.guoanjia.common.credit.config.AliCreditConfig;
import com.guoanjia.common.credit.config.model.GetAccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.guoanjia.common.credit.utils.GlobalConstant.AUTHORIZATION_CODE;

public class GetZhimaCreditScore {

    Logger LOGGER = LoggerFactory.getLogger(GetZhimaCreditScore.class);

    public GetAccessToken getZhimaCreditScore(AliCreditConfig aliCreditConfig, String auth_code){

        /**
         *  获得AccessToke、UserId、RefreshToken的Model
         */
        GetAccessToken getAccessToken = new GetAccessToken();

        /**
         * SDK调用前需要进行初始化
         */
        AlipayClient alipayClient = aliCreditConfig.getAlipayClient();

        /**
         * 用户信息授权request对象
         */
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType(AUTHORIZATION_CODE);
        request.setCode(auth_code);

        try {
            //授权获得AccessToke、UserId、RefreshToken
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            getAccessToken.setAccessToken(oauthTokenResponse.getAccessToken());
            getAccessToken.setUserId(oauthTokenResponse.getUserId());
            getAccessToken.setRefresh_token(oauthTokenResponse.getRefreshToken());
            LOGGER.info("请求AccessToken、UserId返回码：{}，描述：{}", oauthTokenResponse.getCode(),oauthTokenResponse.getMsg());
//            getAccessToken.setAccessToken("accessToken123");
//            getAccessToken.setUserId("userId123");
//            getAccessToken.setRefresh_token("refreshToken123");
        } catch (AlipayApiException e) {
            //处理异常
            LOGGER.error("阿里请求失败，返回异常码：{}，返回异常原因：{}", e.getErrCode(), e.getErrMsg(), e);
        }
        return getAccessToken;
    }
}
