package com.pwang.blog.exception;

public class InvalidValueException extends BusinessException{

    public InvalidValueException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }

    public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_TYPE_VALUE);
    }
}
