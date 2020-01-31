package com.hillel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Report {
    private int id;
    private LocalDate date;
    private String name;
    private String description;
    private Status status;

    @Override
    public String toString() {
        return "Report{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status.getStatusName() +
                '}';
    }
}