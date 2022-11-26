package com.example.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private List<FieldError> fieldErrors; //유효성 검증에 실패한 필드가 여러개일수 있기 때문에 리스트에 저장

    @Getter
    @AllArgsConstructor
    public static class FieldError{ // ErrorResponse 내부에 있으나 inner 클래스 보다는 static 멤버 클래스가 적절한 표현
        private String field;
        private Object rejectedValue;
        private String reason;
    }
}
