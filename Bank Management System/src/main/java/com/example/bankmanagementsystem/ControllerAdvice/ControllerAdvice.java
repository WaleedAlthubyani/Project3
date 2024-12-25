package com.example.bankmanagementsystem.ControllerAdvice;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Api.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse<String>> ApiException(ApiException apiException) {
        String message = apiException.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> MethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        String message = Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getDefaultMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> ConstraintViolationException(ConstraintViolationException e) {
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        String msg=e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class )
    public ResponseEntity<ApiResponse<String>> InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e){
        String msg=e.getMessage();
        return ResponseEntity.status(200).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<String>> DataIntegrityViolationException(DataIntegrityViolationException e){
        String msg=e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<String>> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<String>> NoHandlerFoundException(NoHandlerFoundException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<String>> MissingServletRequestParameter(MissingServletRequestParameterException e) {
        String msg= e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ApiResponse<String>> MissingPathVariable(MissingPathVariableException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> GenericException(Exception e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> HttpMediaTypeNotSupportedException (HttpMediaTypeNotSupportedException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<ApiResponse<String>> ServletRequestBindingException(ServletRequestBindingException e) {
        String msg= e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ApiResponse<String>> EmptyResultDataAccessException(EmptyResultDataAccessException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ApiResponse<String>> TransactionSystemException(TransactionSystemException e) {
        String msg= e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> EntityNotFoundException(EntityNotFoundException e) {
        String msg= e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse<String>> MaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        String msg= e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(msg));
    }
}
