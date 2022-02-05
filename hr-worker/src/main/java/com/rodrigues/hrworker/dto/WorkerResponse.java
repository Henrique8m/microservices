package com.rodrigues.hrworker.dto;

import com.rodrigues.hrworker.entities.Worker;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class WorkerResponse implements Serializable {

    private String name;
    private Double dailyIncome;

    public WorkerResponse toResponse(Worker worker) {
    this.name = worker.getName();
    this.dailyIncome = worker.getDailyIncome();
    return this;
    }

    public WorkerResponse() {
    }

    public WorkerResponse(WorkerResponse response){
        this.name = response.getName();
        this.dailyIncome = response.getDailyIncome();
    }

    public String getName() {
        return name;
    }

    public Double getDailyIncome() {
        return dailyIncome;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDailyIncome(Double dailyIncome) {
        this.dailyIncome = dailyIncome;
    }
}
