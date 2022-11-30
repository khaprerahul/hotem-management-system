package com.guest.response;

import com.guest.exception.ApiError;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponseImpl<T> {
    private HttpStatus status;
    private ApiError apiError;
    private T actualResponse;

}
