package com.chalasani.springboot.controller;

import com.chalasani.springboot.constant.EmployeeConstants;
import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeControllerTest {
  @Mock private EmployeeService employeeService;

  @InjectMocks private EmployeeController employeeController;

  @Test
  void showEmployeesTest() {
    List<Employee> employeeList = getEmployees();
    when(employeeService.getEmployees()).thenReturn(employeeList);

    Model model = new ConcurrentModel();
    employeeController.showEmployees(model);

    List<Employee> employeeListFormDB = (List<Employee>) model.getAttribute("employeeList");

    verify(employeeService).getEmployees();
    assertEquals(2, employeeListFormDB.size());
  }

  @Test
  void showEmployeeFormTest() {
    Model model = new ConcurrentModel();
    employeeController.showEmployeeForm(model);
    Employee employee = (Employee) model.getAttribute(EmployeeConstants.EMPLOYEE);
    assertEquals(0, employee.getId());
    assertNull(employee.getFirstName());
  }

  @Test
  void saveEmployeeTest() {
    Employee employee = getEmployee();
    doNothing().when(employeeService).saveEmployee(isA(Employee.class));
    employeeController.saveEmployee(employee);
  }

  @Test
  void showFormForUpdateTest() {
    Employee employee = getEmployee();
    when(employeeService.findEmployeeById(1)).thenReturn(employee);
    Model model = new ConcurrentModel();
    employeeController.showFormForUpdate(1, model);

    Employee employeeFromDB = (Employee) model.getAttribute(EmployeeConstants.EMPLOYEE);
    assertEquals(1, employeeFromDB.getId());
    assertEquals("Lakshmi", employeeFromDB.getFirstName());
    assertEquals("lakshmi.chalasani@gmail.com", employeeFromDB.getEmail());
  }

  @Test
  void deleteEmployeeTest() {
    doNothing().when(employeeService).deleteEmployeeById(isA(Integer.class));
    employeeController.deleteEmployee(1);
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
