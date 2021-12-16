package com.chalasani.springboot.service;

import com.chalasani.springboot.entity.Employee;

import java.util.List;

public interface EmployeeService {
  public List<Employee> getEmployees();
}
