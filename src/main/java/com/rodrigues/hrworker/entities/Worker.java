package com.rodrigues.hrworker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Entity
@Table(name = "Workers")
public class Worker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerado pelo banco de dados
    private Long id;
    @Column(name = "name")
    @NotNull
    @Size(min = 2, message = "Size")
    private String name;
    @Column(name = "daily_Income")
    @NotNull
    private Double dailyIncome;//ganho diario

    public Worker() {}

    public Worker(Long id, String name, Double dailyIncome) {
        this.id = id;
        this.name = name;
        this.dailyIncome = dailyIncome;
    }
}
