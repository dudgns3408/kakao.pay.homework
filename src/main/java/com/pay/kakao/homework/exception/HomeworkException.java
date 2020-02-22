package com.pay.kakao.homework.exception;

import org.springframework.http.HttpStatus;

public class HomeworkException extends RuntimeException {
    private String errorMessage;
    private HttpStatus status;

    public HomeworkException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
