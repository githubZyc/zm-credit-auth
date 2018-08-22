package com.guoanjia.common.credit.business.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.ZhimaAuthInfoAuthqueryRequest;
import com.alipay.api.response.ZhimaAuthInfoAuthqueryResponse;
import com.guoanjia.common.credit.config.AliCreditConfig;
import com.guoanjia.common.credit.config.model.AuthInfoQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetAuthquery {

    Logger LOGGER = LoggerFactory.getLogger(GetAuthquery.class);

    public String getAuthQuery(AliCreditConfig aliCreditConfig,AuthInfoQuery authInfoQuery){
        AlipayClient alipayClient = aliCreditConfig.getAlipayClient();
        ZhimaAuthInfoAuthqueryRequest request = new ZhimaAuthInfoAuthqueryRequest();
        request.setBizContent(aliCreditConfig.getBizContent(authInfoQuery));
        ZhimaAuthInfoAuthqueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("阿里请求失败，返回异常码：{}，返回异常原因：{}", e.getErrCode(), e.getErrMsg(), e);
            }
        }

        if(response.isSuccess()){
            return "true";
        } else {
            LOGGER.error("调用用户授权失败{},{}",response.getSubCode(),response.getSubMsg());
            //throw new RuntimeException("芝麻用户授权："+response.getSubMsg());
        }
        return "false";
    }

}
