package com.company;

import java.time.LocalDate;
import java.util.Random;

public class Report {
    private int id;
    private LocalDate date;
    private String name;
    private String description;
    private Status status;

    private final String CHARACT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Report(int id) {
        Random random = new Random();
        this.id = id;
        date = LocalDate.of(2019,
                random.nextInt(10) + 1,
                random.nextInt(28) + 1);
        name = "Report_" + id;
        description = generateString(CHARACT, random.nextInt(40) + 10);
        status = new Status(random.nextInt(3) + 1);
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    private String generateString(String characters, int length) {
        Random rng = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    @Override
    public String toString() {
        return "Report{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status.getName() +
                '}';
    }
}
