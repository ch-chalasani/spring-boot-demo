package com.chalasani.springboot.service;

import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  @Override
  public Employee findEmployeeById(int id) {
    Optional<Employee> result = employeeRepository.findById(id);

    Employee employee = null;

    if (result.isPresent()) {
      employee = result.get();
    } else {
      throw new RuntimeException("Invalid Employee Id - " + id);
    }

    return employee;
  }
}
