package com.gabriel.blog.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ApiErrorHandler {
	

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorMessage retunrError(Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getClass());
		ErrorMessage resp = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now());
		return resp;
	}
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ErrorMessage retunrErrorBadRequest(NotFoundException e) {
		System.out.println(e.getMessage());
		System.out.println(e.getClass());
		ErrorMessage resp = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
		return resp;
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ErrorMessage retunrErrorIllegalArgument(IllegalArgumentException e) {
		System.out.println(e.getMessage());
		System.out.println(e.getClass());
		ErrorMessage resp = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
		return resp;
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorMessage retunrErrorMethodArgumentNotValid(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorMessage resp = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),"bad request", LocalDateTime.now(), errors);
        return resp;
	}
	
	
}
