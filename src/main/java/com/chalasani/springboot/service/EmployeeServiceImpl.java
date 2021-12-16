package com.chalasani.springboot.service;

import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> getEmployees() {
    return employeeRepository.findAllByOrderByLastNameAsc();
  }

  @Override
  public void saveEmployee(Employee employee) {
    employeeRepository.save(employee);
  }
}
