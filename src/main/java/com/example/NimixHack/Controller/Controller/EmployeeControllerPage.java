package com.example.NimixHack.Controller.Controller;

import com.example.NimixHack.Model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/employee")
public class EmployeeControllerPage {

    @GetMapping("/new")
    private String newEmployee(Employee employee, Model model){
        model.addAttribute(employee);
        return "newEmployee";
    }
    @GetMapping("/all_with_team")
    private String allWithTeam(Model model){
        RestTemplate template=new RestTemplate();
        List<Employee> employees = Arrays.asList(Objects.requireNonNull(template.getForObject("http://localhost:8080/employee/api/getAllWithTeamId", Employee[].class)));
        model.addAttribute("AllEmployees",employees);
        return "employee_with_team";
    }
    @GetMapping("/pageBetweenTaroAndCosmo/{employeeid}")
    private String pageBetween(@PathVariable("employeeid")int employeeId,Model model){
        model.addAttribute("employeeId",employeeId);
        return "pageBetweenTaroAndCosmo";
    }
}
