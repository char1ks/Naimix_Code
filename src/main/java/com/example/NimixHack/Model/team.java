package com.example.NimixHack.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
public class team {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nomination")
    private String nomination;

    @Column(name = "team_class")
    private String team_class;

    @JsonBackReference // Изменено на JsonBackReference
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Employee> employees;


    public team() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public String getTeam_class() {
        return team_class;
    }

    public void setTeam_class(String team_class) {
        this.team_class = team_class;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "team{" +
                "id=" + id +
                ", nomination='" + nomination + '\'' +
                ", team_class='" + team_class + '\'' +
                '}';
    }
}