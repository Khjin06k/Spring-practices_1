package com.example.advice;

import com.example.exception.BusinessLogicException;
import com.example.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice //Controller에서 발생하는 예외를 도맡아서 처리하는 애너테이션
public class GlobalExceptionAdvice {

    @ExceptionHandler //Request Body 유효성 검증에 실패했을 때
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        //final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors(); //발생한 에러 정보를 확인 후 Response Body로 전달

        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());

        return response;
    }


    @ExceptionHandler //URI 변수로 넘어오는 값의 유효성 검증에 대한 에러 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(
            ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());

        return response;
    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e){
        System.out.println(e.getExceptionCode().getStatus());
        System.out.println(e.getMessage());

        return new ResponseEntity<>(HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }
}
