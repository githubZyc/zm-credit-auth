package com.guoanjia.common.credit.business.controller;

import com.guoanjia.common.credit.business.service.GetAuthquery;
import com.guoanjia.common.credit.business.service.GetZhimaCreditScore;
import com.guoanjia.common.credit.config.AliCreditConfig;
import com.guoanjia.common.credit.config.model.AuthInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class AliGetAccessTokenUserIdController {

    @Autowired
    private AliCreditConfig aliCreditConfig;

    @PostMapping(path = "receiveAtU")
    public String receieveAccessTokenUserID(@RequestParam(value = "app_id", required = false) String appId,
                                            @RequestParam(value = "scope", required = false) String scope,
                                            @RequestParam(value = "error_scope", required = false) String errorScope,
                                            @RequestParam(value = "state", required = false) String state,
                                            @RequestParam(value = "auth_code", required = false) String authCode) {

        GetZhimaCreditScore getZhimaCreditScore = new GetZhimaCreditScore();
        String getAccessTokenUserIdRefreshToken = aliCreditConfig.getBizContent(getZhimaCreditScore.getZhimaCreditScore(aliCreditConfig,authCode));
        return getAccessTokenUserIdRefreshToken;
    }

}
