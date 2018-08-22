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

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

/**
 * @author AsherLi
 * @version 1.0.00
 */
public class DataFileUtils {

    public static File createTempFile(MultipartFile file) {
        try {
            if (file != null) {
                String originalFilename = file.getOriginalFilename();
                String suffix = originalFilename.substring(Objects.requireNonNull(originalFilename).lastIndexOf("."));
                File tempFile = Files.createTempFile("records", "." + suffix).toFile();
                file.transferTo(tempFile);
                return tempFile;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

}





