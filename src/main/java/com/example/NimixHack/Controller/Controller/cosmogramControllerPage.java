package com.example.NimixHack.Controller.Controller;

import com.example.NimixHack.DTO.CosmogramPersonDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/cosmograms")
public class cosmogramControllerPage {

    @GetMapping("/{employeeId}")
    public String getCosmogramByEmployeeId(@PathVariable("employeeId") int employeeId, Model model){
        model.addAttribute("employeeId",employeeId);
        return "writeDataToCosmogram";
    }
    @PostMapping("/mainCosmo/{employeeId}")
    public String getMainCosmo(@PathVariable("employeeId") int employeeId,@RequestBody String xmlFile,Model model   ){
        xmlFile= xmlFile.substring(1, xmlFile.length() - 1);
        model.addAttribute("xmlFile", xmlFile); // Добавляем xmlFile в модель
        return "cosmogramPage";
    }
}
