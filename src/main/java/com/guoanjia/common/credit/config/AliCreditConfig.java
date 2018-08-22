package com.guoanjia.common.credit.config;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipayHashMap;
import com.guoanjia.common.credit.business.service.GetAuthquery;
import com.guoanjia.common.credit.function.config.AliProperties;
import com.guoanjia.common.credit.utils.BaseGsonBuilder;
import com.guoanjia.common.credit.utils.JsonUtil;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里信用配置类
 *
 * @author Libin
 * @version 1.0.00
 */
@Getter
@Configuration
@EnableConfigurationProperties(AliProperties.class)
public class AliCreditConfig {

    Logger LOGGER = LoggerFactory.getLogger(GetAuthquery.class);

    private AlipayHashMap udfParams; // add user-defined text parameters
    /**
     * 配置参数
     */
    private AliProperties pro;

    /**
     * 阿里服务初始化
     */
    private AlipayClient alipayClient;

    public AliCreditConfig(AliProperties aliProperties) {
        this.pro = aliProperties;
        setAlipayClient();
    }

    /**
     * 阿里服务初始化设置
     * @return
     */
    AlipayClient setAlipayClient(){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("芝麻信用，初始化相关参数：\n{}", JsonUtil.format(BaseGsonBuilder.create().toJson(pro)));
        }

        if (StringUtils.isEmpty(pro.getGatewayUrl())) {
            throw new NullPointerException("gatewayUrl 不能为 NULL!");
        }
        if (StringUtils.isEmpty(pro.getAppId())) {
            throw new NullPointerException("appid 不能为 NULL!");
        }
        if (StringUtils.isEmpty(pro.getPrivateKey())) {
            throw new NullPointerException("privateKey 不能为 NULL!");
        }
        if (StringUtils.isEmpty(pro.getFormat())) {
            throw new NullPointerException("format 不能为 NULL!");
        }
        if (StringUtils.isEmpty(pro.getCharset())) {
            throw new NullPointerException("charset 不能为 NULL!");
        }
        if (StringUtils.isEmpty(pro.getAliPublicKey())) {
            throw new NullPointerException("aliPublicKey 不能为 NULL!");
        }
        if (StringUtils.isEmpty(pro.getSignType())) {
            throw new NullPointerException("signType 不能为  NULL!");
        }

        try {
            this.alipayClient = new DefaultAlipayClient(
                    pro.getGatewayUrl(),
                    pro.getAppId(),
                    pro.getPrivateKey(),
                    pro.getFormat(),
                    pro.getCharset(),
                    pro.getAliPublicKey(),
                    pro.getSignType()
            );
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("芝麻信用，初始化失败.", e);
            }
            throw new RuntimeException("芝麻信用功能模块初始化失败");
        }
        return this.alipayClient;
    }

    public String getBizContent(Object object){
        com.google.gson.GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
        return gsonBuilder.create().toJson(object);
    }


}
