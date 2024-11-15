package com.example.NimixHack.Controller.RestController;

import com.example.NimixHack.Model.TarotCard;
import com.example.NimixHack.Service.TarotCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tarot/api")
public class TarotController {

    @Autowired
    private TarotCardService operations;

    @GetMapping("/get/{count}")
    private List<TarotCard> getOneTarot(@PathVariable("count") int count){
        List<TarotCard> listOfTaro=new ArrayList<>();
        for (int i = 0; i <count ; i++) {
            listOfTaro.add(operations.getRandomCard());
        }
        return listOfTaro;
    }
}
