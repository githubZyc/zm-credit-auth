package com.guoanjia.common.credit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**实体类
 * Created by gaozhou on 2018/06/26/.
 */
@Entity
@Table(name ="zhima_score")
public class ZhimaScoreEntity implements Serializable {
    @Id
    @Column(name="id")
    private String id;           //系统UserId（个人中心）
    @Column(name = "aliUserID")
    private String aliUserID;     //阿里返回UserId
    @Column(name = "accessToken")
    private String accessToken;   //accessToken
    @Column(name = "refreshToken")
    private String refreshToken;  //刷新Token
    @Column(name = "zhimaScore")
    private Integer zhimaScore;   //个人芝麻信用分
    @Column(name = "transactionId")
    private String transactionId; //商户请求的唯一标志
    @Column(name = "creditLevel")
    private String creditLevel;   //个人违约情况标记
    @Column(name = "valid_flag")
    private String validFlag;    //标志授权是否被解除
    @Column(name = "creatDate")
    private String creatDate;     //创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAliUserID() {
        return aliUserID;
    }

    public void setAliUserID(String aliUserID) {
        this.aliUserID = aliUserID;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getZhimaScore() {
        return zhimaScore;
    }

    public void setZhimaScore(Integer zhimaScore) {
        this.zhimaScore = zhimaScore;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }
}
