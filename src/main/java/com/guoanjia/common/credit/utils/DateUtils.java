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
 **/
public class DateUtils {

    public static String getDateUnixTimeStamp(){
        long now = System.currentTimeMillis() / 1000L;
        long daySecond = 60 * 60 * 24;
        long dayTime = (now - (now + 8 * 3600) % daySecond)*1000L;
        return String.valueOf(dayTime);
    }

}
