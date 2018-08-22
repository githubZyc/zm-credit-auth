/*
 * Copyright 2013-2018 Tibet CITIC Guoan Real Estate Project Management Co., Ltd. Beijing Branch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.guoanjia.common.credit.utils;

/**
 * @author AsherLi
 * @version 1.0.00
 */
public class GlobalConstant {

    public static class AliProductCode{
        /**
         * 信用积分普惠版产品码
         */
        public static final String CREDIT_SCORE="w1010100000000002733";

        /**
         * 信用行业关注名单普惠版产品码
         */
        public static final String CREDIT_WATCHLIST_BRIEF="w1010100000000002977";

        /**
         * 芝麻认证初始化产品码
         */
        public static final String CERTIFICATION_INITIALIZE = "w1010100000000002978";
    }

    public static class ParameterConstants{

        /**
         * 信用最小允许分数
         */
        public static final int MIN_SCORE = 350;
        /**
         * 信用最大允许分数
         */
        public static final int MAX_SCORE = 950;
        /**
         * 信用类型
         */
        public static final String[] SCENE_CODE = new String[]{
                //负面披露
                "1",
                //信用足迹
                "2",
                //负面+足迹
                "3",
                //信用守护
                "4",
                //负面+守护
                "5",
                //足迹+守护
                "6",
                //负面+足迹+守护
                "7",
                //数据反馈
                "8",
                //骑行
                "32"
        };
        /**
         * 身份认证类型
         */
        public static final String[] IDENTITY_TYPE = new String[]{
                //证件信息入参，现在只支持身份证
                "CERT_INFO"
        };

        /**
         * 识别模式
         */
        public static class BizCode {

            /**
             * 多因子人脸认证
             */
            public static final String FACE = "FACE";
            /**
             * 多因子证照和人脸认证
             */
            public static final String CERT_PHOTO_FACE = "CERT_PHOTO_FACE";
            /**
             * 多因子证照认证
             */
            public static final String CERT_PHOTO = "CERT_PHOTO";
            /**
             * 多因子快捷认证
             */
            public static final String SMART_FACE = "SMART_FACE";
            /**
             * 人脸认证SDK
             */
            public static final String FACE_SDK = "FACE_SDK";
        }

        /**
         * 证件类型
         */
        public static class CertType {
            /**
             * 身份证
             */
            public static final String IDENTITY_CARD = "IDENTITY_CARD";
            /**
             * 护照
             */
            public static final String PASSPORT = "PASSPORT";
            /**
             * 支付宝UID
             */
            public static final String ALIPAY_USER_ID = "ALIPAY_USER_ID";

        }
    }


    public static final String APP_OAUTH_TOKEN_KEY = "app_auth_token";

    /**
     * 执行状态枚举类
     */
    public enum ExecutionStatus {
        /**
         * c成功
         */
        SUCCESS,
        /**
         * 失败
         */
        FAILED,
        /**
         * 未知
         */
        UNKNOWN

    }

    /**
     * 请求状态码
     */
    public static class ExecutionCode {

        /**
         * 接口请求处理成功
         */
        public static final String SUCCESS = "10000";
        /**
         * 接口请求支付类结果用户支付中
         */
        public static final String PAYING = "10003";
        /**
         * 接口请求缺少必要参数
         */
        public static final String MISSING = "40001";
        /**
         * 接口请求非法参数
         */
        public static final String INVALID = "40002";
        /**
         * 接口请求处理失败
         */
        public static final String FAILED = "40004";
        /**
         * 接口请求权限不足
         */
        public static final String PERMISSIONS = "40006";
        /**
         * 接口请求系统异常
         */
        public static final String ERROR = "20000";
        /**
         * 接口请求授权不足
         */
        public static final String AUTH_ERROR = "20001";


    }

    /**
     * 授权查询
     */
    public static final String IDENTITY_PARAM = "identity_param";

    /**
     * grant_type值为authorization_code时，代表用code换取
     */
    public static final String AUTHORIZATION_CODE = "authorization_code";

    /**
     * grant_type值为refresh_token时，代表用refresh_token换取
     */
    public static final String REFRESH_TOKEN = "refresh_token";
}
