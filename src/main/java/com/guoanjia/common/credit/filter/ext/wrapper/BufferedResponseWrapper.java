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

package com.guoanjia.common.credit.filter.ext.wrapper;



import com.guoanjia.common.credit.filter.ext.TeeServletOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author AsherLi0103
 * @version 1.0.00
 */
public class BufferedResponseWrapper extends HttpServletResponseWrapper {

    private HttpServletResponse original;
    private TeeServletOutputStream tee;
    private ByteArrayOutputStream bos;

    public BufferedResponseWrapper(HttpServletResponse response) {
        super(response);
        original = response;
        try {
            this.getOutputStream();
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public String getContent() {
        return bos.toString();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return original.getWriter();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (tee == null) {
            bos = new ByteArrayOutputStream();
            tee = new TeeServletOutputStream(original.getOutputStream(), bos);
        }
        return tee;
    }


}