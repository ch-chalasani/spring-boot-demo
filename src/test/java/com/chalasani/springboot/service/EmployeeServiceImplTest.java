package com.chalasani.springboot.service;

import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeServiceImplTest {

  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private EmployeeServiceImpl employeeService;

  @Test
  void getEmployeesTest() {
    List<Employee> employeeList = getEmployees();
    when(employeeRepository.findAllByOrderByLastNameAsc()).thenReturn(employeeList);
    List<Employee> employeeListFormDB = employeeService.getEmployees();
    assertEquals(2, employeeListFormDB.size());
  }

  @Test
  void saveEmployeeTest() {
    Employee employee = getEmployee();
    when(employeeRepository.save(employee)).thenReturn(employee);
    employeeService.saveEmployee(employee);
  }

  @Test
  void findEmployeeByIdTest() {
    Optional<Employee> optionalEmployee = Optional.of(getEmployee());
    when(employeeRepository.findById(1)).thenReturn(optionalEmployee);
    Employee employee = employeeService.findEmployeeById(1);
    assertEquals(1, employee.getId());
    assertEquals("Lakshmi", employee.getFirstName());
    assertEquals("lakshmi.chalasani@gmail.com", employee.getEmail());
  }

  @Test
  void deleteEmployeeByIdTest() {
    doNothing().when(employeeRepository).deleteById(isA(Integer.class));
    employeeService.deleteEmployeeById(1);
  }

  private List<Employee> getEmployees() {
    List<Employee> employeeList = new ArrayList<>();
    Employee employeeOne = new Employee();
    employeeOne.setId(1);
    employeeOne.setFirstName("Madhu");
    employeeOne.setLastName("Dasari");
    employeeOne.setEmail("madhu.dasari@gmail.com");
    employeeList.add(employeeOne);

    Employee employeeTwo = new Employee();
    employeeTwo.setId(2);
    employeeTwo.setFirstName("Lakshmi");
    employeeTwo.setLastName("Chalasani");
    employeeTwo.setEmail("lakshmi.chalasani@gmail.com");
    employeeList.add(employeeTwo);
    return employeeList;
  }

  private Employee getEmployee() {
    Employee employee = new Employee();
    employee.setId(1);
    employee.setFirstName("Lakshmi");
    employee.setLastName("Chalasani");
    employee.setEmail("lakshmi.chalasani@gmail.com");
    return employee;
  }
}
