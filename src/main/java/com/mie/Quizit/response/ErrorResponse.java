package com.mie.Quizit.response;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private Object data;
    private String message;
}
