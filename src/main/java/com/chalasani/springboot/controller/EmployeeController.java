package com.chalasani.springboot.controller;

import com.chalasani.springboot.entity.Employee;
import com.chalasani.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
  public String showEmployeeForm(Model model) {

    Employee employee = new Employee();

    model.addAttribute("employee", employee);

    return "employee-form";
  }

  @PostMapping("/save")
  public String saveEmployee(@ModelAttribute("employee") Employee employee) {

    employeeService.saveEmployee(employee);

    // use a redirect to prevent duplicate submissions
    return "redirect:/employees/list";
  }

  @GetMapping("/updateForm")
  public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {

    Employee employee = employeeService.findEmployeeById(id);

    model.addAttribute("employee", employee);

    return "employee-form";
  }

  @GetMapping("/delete")
  public String deleteEmployee(@RequestParam("employeeId") int id) {
    employeeService.deleteEmployeeById(id);

    return "redirect:/employees/list";
  }
}
