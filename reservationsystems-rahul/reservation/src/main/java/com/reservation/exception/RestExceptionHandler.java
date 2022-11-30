package com.reservation.exception;

import com.reservation.response.ApiResponseImpl;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    protected ApiResponseImpl<ApiError> handleAccessDeniedException(AccessDeniedException ex){
        ApiError apiError =  new ApiError(HttpStatus.FORBIDDEN);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ReservationEntityNotFoundException.class)
    protected ApiResponseImpl<ApiError> handleEntityNotFound(EntityNotFoundException ex){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ApiResponseImpl<ApiError> buildResponseEntity(ApiError apiError) {
        return new ApiResponseImpl<>(apiError.getStatus(), apiError, null);
    }

    @ExceptionHandler(FeignException.class)
    protected ApiResponseImpl<ApiError> handleFeignException(FeignException ex){
        ApiError apiError =  new ApiError(HttpStatus.valueOf(ex.status()));
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ApiResponseImpl<ApiError> handleException(Exception ex){
        ApiError apiError =  new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
}
