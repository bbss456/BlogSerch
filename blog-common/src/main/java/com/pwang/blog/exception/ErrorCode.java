package com.pwang.blog.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    INVALID_TYPE_VALUE(400, "P001", " Invalid Type Value"),
    INVALID_QUERY_VALUE(400, "P002", " query Not null"),
    INVALID_PAGEMIN_VALUE(400, "P003", "page is less than min"),
    INVALID_PAGEMAX_VALUE(400, "P004", "page is more than max"),
    INVALID_SIZEMin_VALUE(400, "P005", "SIZE is less than min"),
    INVALID_SIZEMAX_VALUE(400, "P006", "SIZE is more than max"),
    SERVICE_ERROR_VALUE(500, "P007", "SERVICE ERROR");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}