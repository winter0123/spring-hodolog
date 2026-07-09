package com.hodolog.api.controller;

import com.hodolog.api.exception.HodologException;
import com.hodolog.api.exception.InvalidRequest;
import com.hodolog.api.exception.PostNotFound;
import com.hodolog.api.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        //ErrorResponse response = new ErrorResponse("400","잘못된 요청입니다.");
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();
        for (FieldError fieldError : e.getFieldErrors()){
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HodologException.class)
    public ResponseEntity<ErrorResponse> hodologException(HodologException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .validation(e.getValidation())
                .message(e.getMessage())
                .build();

/*
    // 구조: [객체 이름] instanceof [클래스 타입]
    // e instanceof InvalidRequest
    if (e instanceof InvalidRequest) { // 1. 정체 확인: InvalidRequest 맞구나?
        // 2. 형변환(Casting): 부모 (HodologException)에서 자식 (InvalidRequest)으로 형변환
        InvalidRequest invalidRequest = (InvalidRequest) e;
        // 3. 자식만의 특수 기능 사용: 부모 레벨에는 없던 'getFieldName()' 메서드 사용가능
        String fieldName = invalidRequest.getFieldName();
        String message = invalidRequest.getMessage();
        // 4. 에러 응답 객체에 "어떤 필드에서 왜 에러가 났는지" 상세 내용을 쏙 넣가
        body.addValidation(fieldName, message);
    }
*/


        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
                .body(body);

        return response;
    }
}
