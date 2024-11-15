package com.example.NimixHack.Controller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tarot")
public class TarotControllerPage {

    @GetMapping("/choose/{employeeId}")
    public String mainPage(@PathVariable("employeeId")int employeeId, Model model){
        model.addAttribute("employeeId",employeeId);
        return "taroChoosePage";
    }
}
