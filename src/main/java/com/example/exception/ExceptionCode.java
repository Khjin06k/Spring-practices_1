package com.example.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_EXISTS(409, "Member Exists"),
    COFFEE_NOT_FOUND(404, "Coffee Not Found"),
    COFFEE_CODE_EXISTS(409, "CoffeeCode Exists"),
    ;

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}
