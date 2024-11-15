package com.example.NimixHack.Controller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
public class teamControllerPage {

    @GetMapping("/create")
    public String createTeam(){
        return "createTeam";
    }

    @GetMapping("/choose/{id}")
    public String chooseTeam(@PathVariable("id")int id, Model model){
        model.addAttribute("employeeId",id);
        return "chooseTeam";
    }
}
