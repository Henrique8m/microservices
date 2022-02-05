package com.rodrigues.hrworker.entities;

import com.rodrigues.hrworker.dto.WorkerResponse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3,message = "Size")
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "daily_Income")
    private Double dailyIncome;
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getDailyIncome() {

        return dailyIncome;
    }
}
