package com.chalasani.springboot.controller;

import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    System.out.println("EmployeeService: " + employeeService + ": Hello");
    List<Employee> employeeList = employeeService.getEmployees();

    model.addAttribute("employeeList", employeeList);

    return "list-employees";
  }
}
