package com.rodrigues.hrpayroll.entities;

import java.io.Serializable;

public class Payment implements Serializable {

    private String name;
    private Double dailyIncome;
    private Integer days;

    public Payment(String name, Double dailyIncome, Integer days) {
        this.name = name;
        this.dailyIncome = dailyIncome;
        this.days = days;
    }

    public double getTotal(){
        return days * dailyIncome;
    }

    public String getName() {
        return name;
    }

    public Double getDailyIncome() {
        return dailyIncome;
    }

    public Integer getDays() {
        return days;
    }
}
