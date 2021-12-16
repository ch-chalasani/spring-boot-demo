package com.chalasani.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  /*
   * This method will show Home Page
   * @return String - Thymeleaf Template Name
   */
  @GetMapping("/")
  public String showIndex() {
    logger.info("Showing home page.");
    return "index";
  }
}
