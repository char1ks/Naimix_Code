package com.example.NimixHack.Service;

import com.example.NimixHack.Model.Rate;
import com.example.NimixHack.Repository.rateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class rateService {
    @Autowired
    private rateRepository operations;

    public List<Rate> getAllRater(){
        return operations.findAll();
    }
    public Rate getConcreteRate(int id){
        return operations.findById(id).orElse(null);
    }
    public Rate getByEmployeeId(int id){
        return operations.findByEmployeeId(id);
    }
    public void saveRate(Rate rate){
        operations.save(rate);
    }
    public void deleteRate(int id){
        operations.deleteById(id);
    }
}
