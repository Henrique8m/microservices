package com.rodrigues.hrpayroll.entities;

import java.io.Serializable;

public class Worker implements Serializable {

    private String name;
    private Double dailyIncome;

    public String getName() {
        return name;
    }

    public Double getDailyIncome() {

        return dailyIncome;
    }
}
