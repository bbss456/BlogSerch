package com.pwang.blog.exception;

public class ServiceException extends BusinessException{

    public ServiceException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }

    public ServiceException(String value) {
        super(value, ErrorCode.SERVICE_ERROR_VALUE);
    }
}
