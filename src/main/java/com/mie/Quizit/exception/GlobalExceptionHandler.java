package com.mie.Quizit.exception;


import com.mie.Quizit.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;

import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        System.out.println(e.getClass());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "Internal Server Error", e.getMessage()));
    }

    // Handle NoHandlerFoundException (404 for missing endpoints)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, "Endpoint Not Found", e.getMessage()));
    }

    // Handle EntityNotFoundException (404 for missing entity)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, "Entity Not Found", e.getMessage()));
    }

    // Handle ConstraintViolationException (400 for validation errors)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "Validation Error", e.getMessage()));
    }

    // Handle IllegalArgumentException (400 for bad input)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "Bad Request", e.getMessage()));
    }

    // Handle AuthenticationException (401 for unauthorized access)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(401, "Unauthorized", e.getMessage()));
    }

    // Handle AccessDeniedException (403 for forbidden access)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedResourceException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(403, "Forbidden", e.getMessage()));
    }


    // Handle AccessDeniedException (403 for forbidden access)
    //    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    //    public ResponseEntity<ErrorResponse> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException e) {
    //        return ResponseEntity.status(HttpStatus.FORBIDDEN)
    //                .body(new ErrorResponse(403, "Forbidden", e.getMessage()));
    //    }

    // Handle MethodArgumentNotValidException (400 for validation errors)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<String> validationErrors = new java.util.ArrayList<>(List.of());

        e.getBindingResult().getAllErrors().forEach((error) -> {

                    String fieldName = ((FieldError) error).getField();
                    String defaultMessage = error.getDefaultMessage();
                    validationErrors.add(defaultMessage);
                }

        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "Validation Error", validationErrors.toString()));
    }


    // Handle Bad Credentials Exception (401 for bad credentials errors)
    //    @ExceptionHandler(BadCredentialsException.class)
    //    public ResponseEntity<ErrorResponse> handleBadCredentialException(BadCredentialsException e) {
    //        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //                .body(new ErrorResponse(401, "Bad Credentials", e.getMessage()));
    //    }


    // Handle Resource Not Found (400 for bad credentials errors)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Resource Not Found", e.getMessage()));
    }

    //    Handle Resource Not Found (400 for bad credentials errors)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleCustomNotReadableException(HttpMessageNotReadableException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Resource Not Found", e.getMessage()));
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<String> handleMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body("The requested media type is not supported. Please use Accept: image/png");
    }
}