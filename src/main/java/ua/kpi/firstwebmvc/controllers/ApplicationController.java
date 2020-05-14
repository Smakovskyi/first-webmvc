package ua.kpi.firstwebmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.kpi.firstwebmvc.services.StudentService;

@Controller
public class ApplicationController {

  @Autowired
  private StudentService studentService;

  @GetMapping({"/", "/home"})
  public String home() {
    return "home";
  }

  @GetMapping("/students")
  public String list(Model model) {
    model.addAttribute("results", studentService.getAll());
    return "students";
  }

  @GetMapping("/students/delete/{id}")
  public String delete(Model model,@PathVariable("id") Integer id){
    studentService.deleteStudent(id);
    model.addAttribute("results", studentService.getAll());
    return "students";
  }

}
