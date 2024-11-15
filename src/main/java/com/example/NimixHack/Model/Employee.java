package com.example.NimixHack.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private String birth_date;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private team team;

    @Column(name = "birth_place")
    private String birth_place;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_tarot_card",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "tarot_card_id")
    )
    private List<TarotCard> cards;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL) // Обратная связь с Rate
    @JsonIgnore // Добавьте эту аннотацию, чтобы избежать рекурсии
    private List<Rate> rates;

    public Employee() {}

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public com.example.NimixHack.Model.team getTeam() {
        return team;
    }

    public void setTeam(com.example.NimixHack.Model.team team) {
        this.team = team;
    }

    public List<TarotCard> getCards() {
        return cards;
    }

    public void setCards(List<TarotCard> cards) {
        this.cards = cards;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

}