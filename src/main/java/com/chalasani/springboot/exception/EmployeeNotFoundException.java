package com.chalasani.springboot.exception;

public class EmployeeNotFoundException extends RuntimeException {
  public EmployeeNotFoundException(String message) {
    super(message);
  }

  public EmployeeNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public EmployeeNotFoundException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
