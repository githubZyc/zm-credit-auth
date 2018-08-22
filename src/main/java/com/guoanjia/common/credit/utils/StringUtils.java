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

import java.text.MessageFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author AsherLi
 * @version 1.0.00
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final Pattern FORMAT_REGEX=Pattern.compile("\\{(\\d)}");

    // TODO 该方法待修正
    public static String formatWithNum(final String sourceMsg, final String template, final Object... args) {
        if (isBlank(sourceMsg)) {
            return "";
        }
        if (Objects.nonNull(args) && args.length > 0) {
            if (isBlank(template)) {
                return MessageFormat.format(sourceMsg, args);
            } else {
                return MessageFormat.format(template, args);
            }
        } else if (isNotBlank(template)) {
            return template;
        } else {
            return "";
        }
    }

    public static String formatWithNum(String template, Object... args) {
        if (Objects.nonNull(args) && args.length > 0) {
            final Matcher m =FORMAT_REGEX.matcher(template);
            while (m.find()) {
                String arg =(String) args[Integer.parseInt(m.group(1)) - 1];
                if (Objects.isNull(arg)) {
                    arg = "";
                }
                template = template.replace(m.group(), arg);
            }
        }
        return template;
    }

    public static String format(String template, Object... args) {
        for (Object arg : args) {
            if (Objects.isNull(arg)) {
                arg = "";
            }
            template = template.replaceFirst("\\{}", String.valueOf(arg));
        }
        return template;
    }

    public static String createMessage(String message, String defaultMessage, Object... args) {
        if (isBlank(message)) {
            return format(defaultMessage, args);
        }
        return format(message, args);
    }


}
