package com.example.NimixHack.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnore // Добавьте аннотацию, чтобы избежать рекурсии
    private Employee employee; // Связь с Employee

    @Column(name = "rate")
    private String rate;

    // Конструктор
    public Rate() {}

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", employee=" + employee +
                ", rate='" + rate + '\'' +
                '}';
    }
}