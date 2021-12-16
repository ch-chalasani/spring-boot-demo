package com.chalasani.springboot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @ExceptionHandler
  public String handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
    logger.error("EmployeeNotFoundException occurred: {}", exception.getMessage());
    return "user-error";
  }

  @ExceptionHandler
  public String handleGenericException(Exception exception) {
    logger.error("Exception occurred: {}", exception.getMessage());
    return "system-error";
  }
}
