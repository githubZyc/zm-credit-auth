package com.guoanjia.common.credit.business.controller;

import com.guoanjia.common.credit.business.service.GetAuthquery;
import com.guoanjia.common.credit.config.AliCreditConfig;
import com.guoanjia.common.credit.config.model.AuthInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author libin
 * @version 1.0.00
 */
@RestController
@RequestMapping("/")
public class AliGetAuthQueryController {

    @Autowired
    private AliCreditConfig aliCreditConfig;


    /**
     *  查询用户是否授权
     * @param authInfoQuery
     * @return
     */
    @PostMapping(path = "getAuthQuery")
    public String getAuthQuery(@RequestBody AuthInfoQuery authInfoQuery){
        GetAuthquery getAuthquery = new GetAuthquery();
        String authFlag = getAuthquery.getAuthQuery(aliCreditConfig,authInfoQuery);
        return authFlag;
    }

}
