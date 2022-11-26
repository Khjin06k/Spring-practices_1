package com.example.response;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private List<FieldError> fieldErrors; //유효성 검증에 실패한 필드가 여러개일수 있기 때문에 리스트에 저장
    private List<FieldError.ConstraintViolationError> violationErrors;

    private ErrorResponse(List<FieldError> fieldErrors, List<FieldError.ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    public static ErrorResponse of(BindingResult bindingResult){
        return new ErrorResponse(FieldError.of(bindingResult), null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> violations){
        return new ErrorResponse(null, FieldError.ConstraintViolationError.of(violations));
    }

    @Getter
    public static class FieldError{ // ErrorResponse 내부에 있으나 inner 클래스 보다는 static 멤버 클래스가 적절한 표현
        private String field;
        private Object rejectedValue;
        private String reason;

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                    bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ?
                                    "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }

        @Getter
        public static class ConstraintViolationError {
            private String propertyPath;
            private Object rejectedValue;
            private String reason;

            private ConstraintViolationError(String propertyPath, Object rejectedValue,
                                             String reason) {
                this.propertyPath = propertyPath;
                this.rejectedValue = rejectedValue;
                this.reason = reason;
            }

            public static List<ConstraintViolationError> of(
                    Set<ConstraintViolation<?>> constraintViolations) {
                return constraintViolations.stream()
                        .map(constraintViolation -> new ConstraintViolationError(
                                constraintViolation.getPropertyPath().toString(),
                                constraintViolation.getInvalidValue().toString(),
                                constraintViolation.getMessage()
                        )).collect(Collectors.toList());
            }
        }
    }
}
