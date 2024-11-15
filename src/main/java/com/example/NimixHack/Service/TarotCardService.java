package com.example.NimixHack.Service;

import com.example.NimixHack.Model.TarotCard;
import com.example.NimixHack.Repository.TarotCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class TarotCardService {

    @Autowired
    private TarotCardRepository operations;

    public List<TarotCard> getAllCards(){
        return operations.findAll();
    }
    public TarotCard getConcreteCard(int id){
        return operations.findById(id).orElse(null);
    }
    public TarotCard getRandomCard(){
        List<TarotCard> tarots=new ArrayList<>(getAllCards());
        if(!tarots.isEmpty()){
            Collections.shuffle(tarots);
            return tarots.getFirst();
        }
        return null;
    }
    public void saveCard(TarotCard card){
        operations.save(card);
    }
    public void updateCard(int id,TarotCard card){
        card.setId(id);
        operations.save(card);
    }
    public void deleteCard(int id){
        operations.deleteById(id);
    }
}
