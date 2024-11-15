package com.example.NimixHack.Controller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultPages {

    @GetMapping("/mainPage")
    private String mainPage(){
        return "mainPage";
    }
}
