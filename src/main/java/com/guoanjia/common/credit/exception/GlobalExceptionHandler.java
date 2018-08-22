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

package com.guoanjia.common.credit.exception;

import com.guoanjia.common.credit.config.SystemConfiguration;
import com.guoanjia.common.credit.config.model.DebugResult;
import com.guoanjia.common.credit.config.model.Result;
import com.guoanjia.common.credit.config.model.ResultEnum;
import com.guoanjia.common.credit.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.*;

import static com.guoanjia.common.credit.config.model.ResultEnum.EXCEPTION;
import static com.guoanjia.common.credit.config.model.ResultEnum.PARAMETER_VALIDATE_FAILED;

/**
 * 全局异常解析器
 *
 * @author AsherLi
 * @version 1.0.00
 */
@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    private Boolean debug;

    public GlobalExceptionHandler(SystemConfiguration configuration) {
        this.debug = configuration.getProperties().getResult().getDebug();
    }

    @ExceptionHandler(value = {Exception.class, Throwable.class})
    @ResponseBody
    public Result exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
       // e.printStackTrace();
        int code;
        String message;
        if (e instanceof ParameterException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getLocalizedMessage();
            response.setStatus(code);
        } else if (e instanceof GlobalException) {
            final ResultEnum resultEnum = ((GlobalException) e).getResultEnum();
            code = resultEnum.getCode();
            message = resultEnum.getMsg();
        } else if (e instanceof MethodArgumentNotValidException) {

            final List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            final List<String> messages = new ArrayList<>(allErrors.size() - 1);
            allErrors.forEach(item -> {
                String field = Objects.requireNonNull(item.getCodes())[1].split("\\.")[1];
                String defaultMessage = item.getDefaultMessage();
                messages.add("参数 " + field + " " + defaultMessage);
            });
            message = Arrays.toString(messages.toArray());
            code = HttpServletResponse.SC_BAD_REQUEST;
            response.setStatus(code);
        } else if (e instanceof ConstraintViolationException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            final Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            message = constraintViolations.stream().findFirst().map(ConstraintViolation::getMessage).orElse("");
            response.setStatus(code);
        } else if (e instanceof MissingPathVariableException) {
            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            message = e.getMessage();
            response.setStatus(code);
        } else if (e instanceof AsyncRequestTimeoutException) {
            code = HttpServletResponse.SC_SERVICE_UNAVAILABLE;
            if (!response.isCommitted()) {
                response.setStatus(code);
            }
            message = e.getMessage();
        } else if (e instanceof MissingServletRequestParameterException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getMessage();
            response.setStatus(code);
        } else if (e instanceof HttpMessageNotReadableException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getMessage();
            response.setStatus(code);
        } else if (e instanceof ServletRequestBindingException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getMessage();
            response.setStatus(code);
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            final List<MediaType> mediaTypes = ((HttpMediaTypeNotSupportedException) e).getSupportedMediaTypes();
            code = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            message = e.getMessage();
            response.setStatus(code);
            if (!CollectionUtils.isEmpty(mediaTypes)) {
                response.setHeader("Accept", MediaType.toString(mediaTypes));
            }
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            code = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
            message = e.getMessage();
            response.setStatus(code);
            final String[] supportedMethods = ((HttpRequestMethodNotSupportedException) e).getSupportedMethods();
            if (Objects.nonNull(supportedMethods)) {
                response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedMethods, ", "));
            }
        } else if (e instanceof HttpMediaTypeNotAcceptableException) {
            code = HttpServletResponse.SC_NOT_ACCEPTABLE;
            message = e.getMessage();
            response.setStatus(code);
        } else if (e instanceof ConversionNotSupportedException) {
            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            message = e.getMessage();
            response.setStatus(code);
            request.setAttribute("javax.servlet.error.exception", e);
        } else if (e instanceof TypeMismatchException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getMessage();
            response.setStatus(code);
        } else if (e instanceof HttpMessageNotWritableException) {
            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            message = e.getMessage();
            response.setStatus(code);
            request.setAttribute("javax.servlet.error.exception", e);
        } else if (e instanceof MissingServletRequestPartException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getMessage();
            response.setStatus(code);
        } else if (e instanceof NoHandlerFoundException) {
            code = HttpServletResponse.SC_NOT_FOUND;
            message = e.getMessage();
            response.setStatus(code);
        } else if (e instanceof BindException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            final List<ObjectError> allErrors = ((BindException) e).getBindingResult().getAllErrors();
            final List<String> messages = new ArrayList<>(allErrors.size() - 1);
            allErrors.forEach(item -> {
                String field = Objects.requireNonNull(item.getCodes())[1].split("\\.")[1];
                String defaultMessage = item.getDefaultMessage();
                messages.add("参数 " + field + " " + defaultMessage);
            });
            message = Arrays.toString(messages.toArray());
            response.setStatus(code);
        } else if (e instanceof ArithmeticException) {
            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            response.setStatus(code);
            message = e.getMessage();
        } else if (e instanceof SQLException) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            message = e.getMessage();
//            final String sqlState = ((SQLException) e).getSQLState();
            code = ((SQLException) e).getErrorCode();
        } else if (e instanceof NullPointerException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getLocalizedMessage();
        } else if (e instanceof IllegalStateException) {
            code = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getLocalizedMessage();
        } else {
            code = ResultEnum.UN_KNOW_ERROR.getCode();
            message = e.getLocalizedMessage();
//            response.setStatus(-1);
//            response.setStatus(code);
        }

        if (debug) {
            final ExceptionInfo exceptionInfo = createExceptionInfo(request, e);
            LOGGER.error("异常，异常码：{}，异常原因：{},异常简介：\n{}", code, message, JsonUtil.format(exceptionInfo.toString()));
            return new DebugResult<>(EXCEPTION.setCode(code).setMsg(message), exceptionInfo);
        }
        LOGGER.error("异常，异常码：{}，异常原因：{}", code, message);
        return new Result<>(EXCEPTION.setCode(code).setMsg(message));
    }


    private ExceptionInfo createExceptionInfo(HttpServletRequest request, Exception exception) {
        final String method = request.getMethod();
        final String remoteAddr = request.getRemoteAddr();
        final String contentType = request.getContentType();
        final StringBuffer requestURL = request.getRequestURL();
        final LinkedList<String> stackTraces = new LinkedList<>();
        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            final String className = stackTraceElement.getClassName();
            final String fileName = stackTraceElement.getFileName();
            final String methodName = stackTraceElement.getMethodName();
            final int lineNumber = stackTraceElement.getLineNumber();
            stackTraces.add(fileName + "" + className + "." + methodName + "(" + fileName + ":" + lineNumber + ")");
        });
        return ExceptionInfo.builder().url(requestURL.toString()).contentType(contentType).method(method).remoteAddr(remoteAddr).stackTraces(stackTraces).build();
    }

}

