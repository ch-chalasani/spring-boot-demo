package com.chalasani.springboot.service;

import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.exception.EmployeeNotFoundException;
import com.chalasani.springboot.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  /*
   * This method will retrieve all the Employees from database
   *
   * @return List - List of Employees
   */
  @Override
  public List<Employee> getEmployees() {
    logger.info("Calling EmployeeRepository to retrieve list of employees");
    return employeeRepository.findAllByOrderByLastNameAsc();
  }

  /*
   * This method will create a new Employee in the Database
   *
   * @param employee - Employee object
   */
  @Override
  public void saveEmployee(Employee employee) {
    logger.info(
        "Calling EmployeeRepository to save Employee with first name: {}", employee.getFirstName());
    employeeRepository.save(employee);
  }

  /*
   * This method will retrieve the Employee object using Employee id
   *
   * @param id - Employee id
   * @return Employee - Employee object
   */
  @Override
  public Employee findEmployeeById(int id) {
    Optional<Employee> result = employeeRepository.findById(id);

    Employee employee = null;

    if (result.isPresent()) {
      logger.info("Successfully retrieved Employee for the Id: " + id);
      employee = result.get();
    } else {
      logger.error("Unable to find Employee with Id: " + id);
      throw new EmployeeNotFoundException("Invalid Employee Id: " + id);
    }

    return employee;
  }

  /*
   * This method will delete the Employee object using Employee id
   *
   * @param id - Employee id
   */
  @Override
  public void deleteEmployeeById(int id) {
    logger.info("Deleting Employee with id: " + id);
    employeeRepository.deleteById(id);
  }
}
