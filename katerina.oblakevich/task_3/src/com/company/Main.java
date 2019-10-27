package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        ReportStatisticService service = new ReportStatisticService(reports);

        for (int i = 0; i < 1000; i++) {
            Report report = new Report(i);
            reports.add(report);
        }

        System.out.println((service.getReportsByStatus(
                new Status(Status.STATUS_RED))));

        System.out.println(service.getReportsCountByStatus(
                new Status(Status.STATUS_YELLOW)));

        System.out.println(service.getReportsFromDate(
                LocalDate.of(2019, 10, 22)));

        System.out.println(service.getReportsToDate(
                LocalDate.of(2019, 10, 22)));

        System.out.println(service.getReportsInInterval(
                LocalDate.of(2019, 4, 1),
                LocalDate.of(2019, 6, 3)));

        System.out.println(service.getLastReportsLog(3));
    }





}
