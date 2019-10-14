package com.hurko.controller.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class BaseHandler {

    @ExceptionHandler(value = InvalidInputValueException.class)
    public BaseResponse handlerInvalidInputValueException(InvalidInputValueException exception) {
        return BaseResponse.buildErrorResponse(500, exception.getMessage());
    }

    @ExceptionHandler(value = IncorrectMoneyValue.class)
    public BaseResponse handlerIncorrectMoneyValue(IncorrectMoneyValue exception) {
        return BaseResponse.buildErrorResponse(500, exception.getMessage());
    }

    @ExceptionHandler(value = IncorrectUserException.class)
    public BaseResponse handlerIncorrectUserException(IncorrectUserException exception) {
        return BaseResponse.buildErrorResponse(500, exception.getMessage());
    }

    @Setter
    @Getter
    private static class BaseResponse {

        private Integer errorCode = 200;
        private Boolean success = true;
        private String errorMessage;

        public static BaseResponse buildErrorResponse(int errorCode, String errorMessage) {
            BaseResponse response = new BaseResponse();
            response.setErrorCode(errorCode);
            response.setErrorMessage(errorMessage);
            response.setSuccess(Boolean.FALSE);
            return response;
        }
    }
}
