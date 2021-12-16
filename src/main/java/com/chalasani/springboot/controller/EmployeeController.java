package com.chalasani.springboot.controller;

import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

  private EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/list")
  public String showEmployees(Model model) {
    List<Employee> employeeList = employeeService.getEmployees();

    model.addAttribute("employeeList", employeeList);

    return "list-employees";
  }

  @GetMapping("/employeeForm")
  public String showEmployeeForm(Model theModel) {

    Employee employee = new Employee();

    theModel.addAttribute("employee", employee);

    return "employee-form";
  }

  @PostMapping("/save")
  public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

    employeeService.saveEmployee(theEmployee);

    // use a redirect to prevent duplicate submissions
    return "redirect:/employees/list";
  }
}
