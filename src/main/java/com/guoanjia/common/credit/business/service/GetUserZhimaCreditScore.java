package com.guoanjia.common.credit.business.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.ZhimaCreditScoreGetRequest;
import com.alipay.api.response.ZhimaCreditScoreGetResponse;
import com.guoanjia.common.credit.config.AliCreditConfig;
import com.guoanjia.common.credit.config.model.AuthInfoQuery;
import com.guoanjia.common.credit.config.model.ZhimaCreditScoreQueryModel;
import com.guoanjia.common.credit.entity.ZhimaScoreEntity;
import com.guoanjia.common.credit.entity.jpa.ZhimaScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gaozhou on 2018/06/25/.
 */
public class GetUserZhimaCreditScore {
    @Autowired
     private ZhimaScoreRepository zhimaScoreRepository;
    Logger LOGGER = LoggerFactory.getLogger(GetAuthquery.class);

    /**
     * @descrition 查询用户芝麻信用分
     * @param zhimaCreditScoreQueryModel
     * @return 用户芝麻信用分
     */
    public int getUserZhimaCreditScore(AliCreditConfig aliCreditConfig, ZhimaCreditScoreQueryModel zhimaCreditScoreQueryModel) {
        //根据用户id从芝麻信用基本表查valid_flag：标志授权是否被解除
        String userId=zhimaCreditScoreQueryModel.getId();
        String validFlag=zhimaScoreRepository.getvalidFlag(userId);
        if (validFlag.equals("0")){ //有效 直接从芝麻信用基本表取得积分
            int zhimaScore= zhimaScoreRepository.getUserZhimaScore(userId);
            return zhimaScore;
        }

        AlipayClient alipayClient = aliCreditConfig.getAlipayClient();
        //查询是否授权
        AuthInfoQuery authInfoQuery = new AuthInfoQuery();  //查询是否授权model
        authInfoQuery.setIdentity_param("340881199001265117");  //查询是否授权设置入参
        GetAuthquery getAuthquery = new GetAuthquery();  //service
        String authFlag = getAuthquery.getAuthQuery(aliCreditConfig,authInfoQuery);
        if(authFlag.equals("true")){  //授权以后获得该用户的access_token
            //获取access_token
            GetZhimaCreditScore getZhimaCreditScore = new GetZhimaCreditScore();//service
            String accessToken = aliCreditConfig.getBizContent(getZhimaCreditScore.getZhimaCreditScore(aliCreditConfig,"1000"));
            //使用该access_token调阿里接口查询用户芝麻信用分
            ZhimaCreditScoreGetRequest request = new ZhimaCreditScoreGetRequest();
            request.setBizContent("{" +
                    " transaction_id:b42100cdae94-b862-4e27-8458-0c15bd2a15299982201650," +
                    " product_code:w1010100100000000001" +
                    " }");
            ZhimaCreditScoreGetResponse response = null;
            try {
                response = alipayClient.execute(request,accessToken);
                if(response.isSuccess()){ //调用成功 芝麻分录入数据库
                    int zhimaScore = Integer.valueOf(response.getZmScore());
                    ZhimaScoreEntity zhimaScoreEntity=new ZhimaScoreEntity();
                    zhimaScoreEntity.setId(userId);
                    zhimaScoreEntity.setZhimaScore(zhimaScore);
                    zhimaScoreRepository.save(zhimaScoreEntity);
                    return zhimaScore;
                }else {
                    LOGGER.error("调用用户芝麻分查询失败{},{}",response.getSubCode(),response.getSubMsg());
                    getErrorMark(); //调用失败返回失败标志
                }
            } catch (AlipayApiException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("阿里请求失败，返回异常码：{}，返回异常原因：{}", e.getErrCode(), e.getErrMsg(), e);

                }
            }
        }
       return 0;
    }
    public String getErrorMark(){
        return "N/A";
    }

}
