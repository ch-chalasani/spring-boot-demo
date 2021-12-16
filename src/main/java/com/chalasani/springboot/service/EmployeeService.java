package com.chalasani.springboot.service;

import com.chalasani.springboot.entity.Employee;

import java.util.List;

public interface EmployeeService {
  List<Employee> getEmployees();

  void saveEmployee(Employee employee);

  Employee findEmployeeById(int id);
}
