package com.hillel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomReportGenerator {
    private final String CHARACT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public RandomReportGenerator() {
    }

    public List<Report> generateRandomReports(int length) {
        List<Report> reports = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            Report report = new Report();
            report.setId(i);
            report.setDate(LocalDate.of(2019,
                    random.nextInt(10) + 1,
                    random.nextInt(28) + 1));
            report.setName("Report_" + i);
            report.setDescription(generateString(CHARACT, random.nextInt(40) + 10));
            report.setStatus(generateStatusByNumber(random.nextInt(3) + 1));
            reports.add(report);
        }
        return reports;
    }

    private String generateString(String characters, int length) {
        Random rng = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public Status generateStatusByNumber(int statusNumber) {
        if (statusNumber == 1)
            return Status.STATUS_GREEN;
        if (statusNumber == 2)
            return Status.STATUS_YELLOW;
        if (statusNumber == 3)
            return Status.STATUS_RED;
        return null;
    }
}
