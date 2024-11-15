package com.example.NimixHack.Service;

import com.example.NimixHack.Model.team;
import com.example.NimixHack.Repository.teamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class teamService {

    @Autowired
    private teamRepository operations;

    public List<team> getAllTeams(){
        return operations.findAll();
    }
    public team getConcreteTeam(int id){
        return operations.findById(id).orElse(null);
    }
    public void saveTeam(team team){
        operations.save(team);
    }
    public void updateTeam(int id,team team){
        team.setId(id);
        operations.save(team);
    }
    public void deleteTeam(int id){
        operations.deleteById(id);
    }
}
