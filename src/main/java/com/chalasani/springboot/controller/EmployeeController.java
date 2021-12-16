package com.chalasani.springboot.controller;

import com.chalasani.springboot.constant.EmployeeConstants;
import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  private EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/list")
  public String showEmployees(Model model) {
    List<Employee> employeeList = employeeService.getEmployees();
    logger.info("Successfully retrieved list of employees from EmployeeService");
    model.addAttribute("employeeList", employeeList);
    return "list-employees";
  }

  @GetMapping("/employeeForm")
  public String showEmployeeForm(Model model) {
    Employee employee = new Employee();
    model.addAttribute(EmployeeConstants.EMPLOYEE, employee);
    return EmployeeConstants.EMPLOYEE_FORM;
  }

  @PostMapping("/save")
  public String saveEmployee(@ModelAttribute(EmployeeConstants.EMPLOYEE) Employee employee) {
    logger.info(
        "Calling EmployeeService to save Employee with email: {}, firstName: {}, lastName: {}",
        employee.getEmail(),
        employee.getFirstName(),
        employee.getLastName());
    employeeService.saveEmployee(employee);

    // use a redirect to prevent duplicate submissions
    return EmployeeConstants.REDIRECT_EMPLOYEE_LIST;
  }

  @GetMapping("/updateForm")
  public String showFormForUpdate(
      @RequestParam(EmployeeConstants.EMPLOYEE_ID) int id, Model model) {
    Employee employee = employeeService.findEmployeeById(id);
    logger.info("Successfully retrieved Employee details for the id: {}", id);
    model.addAttribute(EmployeeConstants.EMPLOYEE, employee);
    return EmployeeConstants.EMPLOYEE_FORM;
  }

  @GetMapping("/delete")
  public String deleteEmployee(@RequestParam(EmployeeConstants.EMPLOYEE_ID) int id) {
    logger.info("Calling EmployeeService to delete Employee with id: {}", id);
    employeeService.deleteEmployeeById(id);
    return EmployeeConstants.REDIRECT_EMPLOYEE_LIST;
  }
}
