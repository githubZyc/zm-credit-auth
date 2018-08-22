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

package com.guoanjia.common.credit.filter.ext;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;

/**
 * @author AsherLi0103
 * @version 1.0.00
 */
public class BufferedServletInputStream extends ServletInputStream {

    private ByteArrayInputStream byteArrayInputStream;

    public BufferedServletInputStream(ByteArrayInputStream bais) {
        this.byteArrayInputStream = bais;
    }

    @Override
    public int available() {
        return this.byteArrayInputStream.available();
    }

    @Override
    public int read() {
        return this.byteArrayInputStream.read();
    }

    @Override
    public int read(byte[] buf, int off, int len) {
        return this.byteArrayInputStream.read(buf, off, len);
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }
}