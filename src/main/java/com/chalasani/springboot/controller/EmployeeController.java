package com.chalasani.springboot.controller;

import com.chalasani.springboot.constant.EmployeeConstants;
import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

  /*
   * This method will retrieve all the Employees from database and invoke list-employees.html
   *
   * @param model - The Model object
   * @return String - Thymeleaf Template Name
   */
  @GetMapping("/list")
  public String showEmployees(Model model) {
    List<Employee> employeeList = employeeService.getEmployees();
    logger.info("Successfully retrieved list of employees from EmployeeService");
    model.addAttribute("employeeList", employeeList);
    return "list-employees";
  }

  /*
   * This method will show Employee Form
   *
   * @param model - The Model object
   * @return String - Thymeleaf Template Name
   */
  @GetMapping("/employeeForm")
  public String showEmployeeForm(Model model) {
    Employee employee = new Employee();
    model.addAttribute(EmployeeConstants.EMPLOYEE, employee);
    return EmployeeConstants.EMPLOYEE_FORM;
  }

  /*
   * This method will create a new Employee in the Database
   *
   * @param employee - The Employee object
   * @return String - Redirection Path
   */
  @PostMapping("/save")
  public String saveEmployee(
      @ModelAttribute(EmployeeConstants.EMPLOYEE) @Valid Employee employee, BindingResult result) {
    if (result.hasErrors()) {
      logger.error("Employee is not valid: {}", employee);
      return EmployeeConstants.EMPLOYEE_FORM;
    }
    logger.info(
        "Calling EmployeeService to save Employee with email: {}, firstName: {}, lastName: {}",
        employee.getEmail(),
        employee.getFirstName(),
        employee.getLastName());
    employeeService.saveEmployee(employee);

    // use a redirect to prevent duplicate submissions
    return EmployeeConstants.REDIRECT_EMPLOYEE_LIST;
  }

  /*
   * This method will show Employee form by filling his details
   *
   * @param id - The Employee id
   * @param model - The Model object
   * @return String - Thymeleaf Template Name
   */
  @GetMapping("/updateForm")
  public String showFormForUpdate(
      @RequestParam(EmployeeConstants.EMPLOYEE_ID) int id, Model model) {
    Employee employee = employeeService.findEmployeeById(id);
    logger.info("Successfully retrieved Employee details for the id: {}", id);
    model.addAttribute(EmployeeConstants.EMPLOYEE, employee);
    return EmployeeConstants.EMPLOYEE_FORM;
  }

  /*
   * This method will delete the Employee using id
   *
   * @param id - The Employee id
   * @return String - Redirection Path
   */
  @GetMapping("/delete")
  public String deleteEmployee(@RequestParam(EmployeeConstants.EMPLOYEE_ID) int id) {
    logger.info("Calling EmployeeService to delete Employee with id: {}", id);
    employeeService.deleteEmployeeById(id);
    return EmployeeConstants.REDIRECT_EMPLOYEE_LIST;
  }
}
