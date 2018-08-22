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
public class RegexRule {

    /**
     * 身份证号码正则
     */
    public static final String ID_CARD = "\\d{6}(19|20)*[0-99]{2}(0[1-9]{1}|10|11|12)(0[1-9]{1}|1[0-9]|2[0-9]|30|31)(\\w*)";

    /**
     * 护照号码正则
     */
    public static final String PASSPORT = "1[45][0-9]{7}|([P|p|S|s]\\d{7})|([S|s|G|g]\\d{8})|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\\d{8})|([H|h|M|m]\\d{8,10})";

    /**
     * EMAIL正则
     */
    public static final String EMAIL = "('')|(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)";

    /**
     * 电话号码正则
     */
    public static final String TELEPHONE = "('')|(\\d{4}(-*)\\d{8}|\\d{4}(-*)\\d{7}|\\d{3}(-*)\\d{8}|\\d{3}(-*)\\d{7})";

    /**
     * 验证手机号码
     */
    public static final String MOBILE_PHONE = "1(3|4|5|8|7)\\d{9}";

    /**
     * 是否全部为中文
     */
    public static final String CHINESE_CHAR = "[\u4e00-\u9fa5]+";

    /**
     * 检查字符串中是否还有HTML标签
     */
    public static final String HTML_TAG = "<(\\S*?)[^>]*>.*?</\\1>|<.*? />";

    /**
     * 检查URL是否合法
     */
    public static final String SIMPLE_URL = "[a-zA-z]+://[^\\s]*";

    /**
     * URL正则
     */
    public static final String URL_NO_PARAM = "((https|http)?://)?([\\w-]+\\.)+[:\\w-]+(/[\\w-./%]*)?";

    /**
     * 检查IP是否合法
     */
    public static final String IP_ADDRESS_SIMPLE = "\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}";

    /**
     * IP地址正则
     */
    public static final String IP_ADDRESS = "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\." +
            "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\." +
            "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\." +
            "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])";

    /**
     * 检查QQ号是否合法
     */
    public static final String QQ_CODE = "[1-9][0-9]{4,13}";

    /**
     * 检查邮编是否合法
     */
    public static final String POSTCODE = "[1-9]\\d{5}(?!\\d)";

    /**
     * 正整数
     */
    public static final String POSITIVE_INTEGER = "^[0-9]\\d*$";

    /**
     * 正浮点数
     */
    public static final String POSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[0-9]\\d*$";

    /**
     * 整数或小数
     */
    public static final String POSITIVE_DOUBLE = "^[0-9]+(\\.[0-9]+)?$";

    /**
     * 年月日 2012-1-1,2012/1/1,2012.1.1
     */
    public static final String DATE_YMD = "^\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}$";

    /**
     * 日期正则 yyyy-MM-dd
     */
    public static final String YYYYMMDD_REG = "[1-9]\\d{3}([0][1-9]|1[0-2])([0][1-9]|[1-2][0-9]|3[0-1])";

}
