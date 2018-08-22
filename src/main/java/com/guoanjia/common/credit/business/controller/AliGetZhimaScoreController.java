package com.guoanjia.common.credit.business.controller;

import com.guoanjia.common.credit.business.service.GetUserZhimaCreditScore;
import com.guoanjia.common.credit.config.AliCreditConfig;
import com.guoanjia.common.credit.config.model.ZhimaCreditScoreQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**查询个人芝麻信用分控制类
 * Created by gaozhou on 2018/06/25/.
 */
@RestController
@RequestMapping("/")
public class AliGetZhimaScoreController {

    @Autowired
    private AliCreditConfig aliCreditConfig;

    /**
     *  查询用户芝麻信用分
     * @param zhimaCreditScoreQueryModel
     * @return 用户芝麻信用分
     */
    @PostMapping(path = "getZhimaScoreQuery")
    public Integer getZhimaCreditScoreQuery(@RequestBody ZhimaCreditScoreQueryModel zhimaCreditScoreQueryModel){
        GetUserZhimaCreditScore getUserZhimaCreditScoreService = new GetUserZhimaCreditScore();
        int userZhimaCreditScore = getUserZhimaCreditScoreService.getUserZhimaCreditScore(aliCreditConfig,zhimaCreditScoreQueryModel);
        return userZhimaCreditScore;
    }
}
